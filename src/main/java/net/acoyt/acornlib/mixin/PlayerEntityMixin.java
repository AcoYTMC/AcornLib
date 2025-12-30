package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.CanConsumeItemEvent;
import net.acoyt.acornlib.api.item.*;
import net.acoyt.acornlib.api.util.ItemUtils;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.init.AcornCriterions;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(
            method = "getDisplayName",
            at = @At("RETURN")
    )
    public Text acornLib$applyFriendFormattingToName(Text original) {
        return AcornConfig.allowSupporterNameColors ? AcornLibUtils.stylizeNames(this.getUuid(), original) : original;
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;getAttackCooldownProgress(F)F"
            )
    )
    private void acornlib$spawnCustomHitParticlesAndPlayCustomHitSound(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (this.getAttackCooldownProgress(0.5F) > 0.9F) {
            Item var4 = this.getMainHandStack().getItem();
            if (var4 instanceof CustomHitParticleItem hitParticleItem) {
                hitParticleItem.spawnHitParticles(player, target);
            }

            var4 = this.getMainHandStack().getItem();
            if (var4 instanceof CustomHitSoundItem hitSoundItem) {
                hitSoundItem.playHitSound(player, target);
            }
        }

        if (this.getAttackCooldownProgress(0.5F) > 0.9F) {
            ItemStack stack = this.getMainHandStack();
            if (stack.contains(AcornComponents.HIT_PARTICLE)) {
                ParticleEffect par = ParticleTypes.SWEEP_ATTACK;
                if (stack.get(AcornComponents.HIT_PARTICLE) != null) {
                    SimpleParticleType reg = (SimpleParticleType)Registries.PARTICLE_TYPE.get(Objects.requireNonNull(stack.get(AcornComponents.HIT_PARTICLE)).particle());
                    if (reg != null) {
                        par = reg;
                    }
                }

                int count = stack.getOrDefault(AcornComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT).count();

                ParticleUtils.spawnSweepParticles(par, count, player);
            }

            if (stack.contains(AcornComponents.SWEEP_PARTICLE)) {
                SweepParticleComponent advHitParticle = stack.get(AcornComponents.SWEEP_PARTICLE);
                assert advHitParticle != null;
                int base = advHitParticle.baseColor();
                int shadow = advHitParticle.shadowColor();

                ParticleUtils.spawnSweepParticles(new SweepParticleEffect(base, shadow), player);
            }

            if (stack.contains(AcornComponents.HIT_SOUND)) {
                SoundEvent soundEvent = SoundEvents.INTENTIONALLY_EMPTY;
                if (stack.get(AcornComponents.HIT_SOUND) != null) {
                    SoundEvent event = SoundEvent.of(stack.getOrDefault(AcornComponents.HIT_SOUND, HitSoundComponent.DEFAULT).soundEvent());
                    if (event.getId() != null) {
                        soundEvent = event;
                    }
                }

                boolean bl = stack.getOrDefault(AcornComponents.HIT_SOUND, HitSoundComponent.DEFAULT).randomPitch();
                this.playSound(soundEvent, 1.0F, bl ? (float) (1.0F + player.getRandom().nextGaussian() / 10f) : 1.0F);
            }
        }
    }

    @Inject(method = "canConsume", at = @At("HEAD"), cancellable = true)
    private void noEat(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        ItemStack stack = player.getActiveItem();
        Boolean original = cir.getReturnValue();
        if (original != null && stack != null) {
            boolean bl = CanConsumeItemEvent.EVENT.invoker().canConsume(player, stack);
            cir.setReturnValue(bl && original);
        }
    }

    @Inject(
            method = "takeShieldHit",
            at = @At("HEAD")
    )
    private void silly(LivingEntity attacker, CallbackInfo ci) {
        ItemStack stack = attacker.getMainHandStack();
        if (stack.getItem() instanceof ShieldBreaker sb) {
            this.getItemCooldownManager().set(Items.SHIELD, sb.shieldCooldown(stack));
            this.clearActiveItem();
            this.getWorld().sendEntityStatus(this, EntityStatuses.BREAK_SHIELD);
        }

    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"
            )
    )
    private void grantCriticalHitCriterion(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player instanceof ServerPlayerEntity serverPlayer) {
            AcornCriterions.CRITICAL_HIT.trigger(serverPlayer);
        }

        ItemStack stack = this.getStackInHand(Hand.MAIN_HAND);
        if (stack.getItem() instanceof CritEffectItem scythe) {
            scythe.critEffect(player, player.getMainHandStack(), target);
        }
    }

    @WrapOperation(
            method = "applyDamage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;modifyAppliedDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"
            )
    )
    private float swordBlock(PlayerEntity player, DamageSource source, float amount, @NotNull Operation<Float> original) {
        float base = original.call(player, source, amount);
        for (ItemStack stack : ItemUtils.getHeldStacks(player)) {
            if (player.isUsingItem() && ItemStack.areEqual(player.getActiveItem(), stack)) {
                if (!this.getWorld().isClient() && (!source.isIn(DamageTypeTags.BYPASSES_SHIELD) || source.isOf(DamageTypes.FALL)) && stack.getItem() instanceof AdvancedBlockingItem blockingItem) {
                    Vec3d damagePos = source.getPosition();
                    if (source.isOf(DamageTypes.FALL)) {
                        if (MiscUtils.isLookingDown(player)) {
                            this.getWorld().playSoundFromEntity(null, this, blockingItem.blockSound(), SoundCategory.HOSTILE, 1.0F, 1.0F + this.getWorld().getRandom().nextFloat() * 0.4F);
                            blockingItem.absorbDamage(player, source, stack, base / 2.0F);
                            return base / 4.0F;
                        }
                    } else if (damagePos != null) {
                        Vec3d rotVec = this.getRotationVec(1.0F);

                        Vec3d difference = damagePos.relativize(this.getEyePos()).normalize();
                        double angle = difference.dotProduct(rotVec);
                        if (!(angle < -1.0F) && angle < -0.35) {
                            this.getWorld().playSoundFromEntity(null, this, blockingItem.blockSound(), SoundCategory.HOSTILE, 1.0F, 1.0F + this.getWorld().getRandom().nextFloat() * 0.4F);
                            blockingItem.absorbDamage(player, source, stack, base);
                            return base / 2.0F;
                        }
                    }
                }
            }
        }

        return base;
    }
}
