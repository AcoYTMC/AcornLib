package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.item.AdvancedBlockingItem;
import net.acoyt.acornlib.api.item.CritEffectItem;
import net.acoyt.acornlib.api.item.HitEffectsItem;
import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.acoyt.acornlib.api.util.ItemUtils;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.acoyt.acornlib.impl.index.AcornCriteria;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

/**
 * @author AcoYT
 */
@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow public abstract float getAttackStrengthScale(float a);

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyExpressionValue(
            method = "createAttributes",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;createLivingAttributes()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;"
            )
    )
    private static AttributeSupplier.Builder acornlib$addOpacity(AttributeSupplier.Builder original) {
        return original.add(AcornAttributes.OPACITY, 1.0);
    }

    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    public Component acornlib$applyFriendFormattingToName(Component original) {
        return AcornLib.isMidnightLibLoaded && AcornConfig.allowSupporterNameColors ? AcornUtil.stylizeNames(this.getUUID(), original) : original;
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getAttackStrengthScale(F)F"
            )
    )
    private void acornlib$runHitEffects(Entity entity, CallbackInfo ci) {
        Player player = (Player)(Object)this;
        if (this.getAttackStrengthScale(0.5F) > 0.9F) {
            Item item = this.getMainHandItem().getItem();
            if (item instanceof HitEffectsItem hitEffects) {
                hitEffects.runHitEffects(player, entity);
            }
        }

        if (this.getAttackStrengthScale(0.5F) > 0.9F) {
            ItemStack stack = this.getMainHandItem();
            if (stack.has(AcornDataComponents.HIT_PARTICLE)) {
                ParticleOptions par = ParticleTypes.SWEEP_ATTACK;
                if (stack.get(AcornDataComponents.HIT_PARTICLE) != null) {
                    SimpleParticleType reg = (SimpleParticleType)BuiltInRegistries.PARTICLE_TYPE.getValue(Objects.requireNonNull(stack.get(AcornDataComponents.HIT_PARTICLE)).particle());
                    if (reg != null) {
                        par = reg;
                    }
                }

                int count = stack.getOrDefault(AcornDataComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT).count();

                ParticleUtils.spawnSweepParticles(par, count, player);
            }

            if (stack.has(AcornDataComponents.SWEEP_PARTICLE)) {
                SweepParticleComponent advHitParticle = stack.getOrDefault(AcornDataComponents.SWEEP_PARTICLE, SweepParticleComponent.DEFAULT);
                int base = advHitParticle.baseColor();
                int shadow = advHitParticle.shadowColor();

                ParticleUtils.spawnSweepParticles(new SweepParticleEffect(base, shadow), player);
            }

            if (stack.has(AcornDataComponents.HIT_SOUND)) {
                SoundEvent soundEvent = SoundEvents.EMPTY;
                if (stack.get(AcornDataComponents.HIT_SOUND) != null) {
                    SoundEvent event = SoundEvent.createVariableRangeEvent(stack.getOrDefault(AcornDataComponents.HIT_SOUND, HitSoundComponent.DEFAULT).soundEvent());
                    if (event.location() != null) {
                        soundEvent = event;
                    }
                }

                boolean bl = stack.getOrDefault(AcornDataComponents.HIT_SOUND, HitSoundComponent.DEFAULT).randomPitch();
                this.playSound(soundEvent, 1.0F, bl ? (float) (1.0F + player.getRandom().nextGaussian() / 10f) : 1.0F);
            }
        }
    }

    @Inject(method = "blockUsingItem", at = @At("HEAD"))
    private void acornlib$customShieldCooldown(ServerLevel level, LivingEntity attacker, CallbackInfo ci) {
        ItemStack stack = attacker.getMainHandItem();
        ItemStack shield = this.getItemBlockingWith();
        BlocksAttacks component = shield != null ? shield.get(DataComponents.BLOCKS_ATTACKS) : null;
        if (stack.getItem() instanceof ShieldBreaker sb) {
            float cooldown = sb.getShieldCooldown(stack);
            if (cooldown > 0.0F && component != null) {
                component.disable(level, this, cooldown, shield);
            }
        }

    }

    @Inject(
            method = "attackVisualEffects",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;crit(Lnet/minecraft/world/entity/Entity;)V"
            )
    )
    private void acornlib$grantCriticalHitCriterion(Entity entity, boolean criticalAttack, boolean sweepAttack, boolean fullStrengthAttack, boolean stabAttack, float magicBoost, CallbackInfo ci) {
        Player player = (Player)(Object)this;
        if (player instanceof ServerPlayer serverPlayer) {
            AcornCriteria.CRITICAL_HIT.trigger(serverPlayer);
        }

        ItemStack stack = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (stack.getItem() instanceof CritEffectItem scythe) {
            scythe.critEffect(player, player.getMainHandItem(), entity);
        }
    }

    @WrapOperation(
            method = "actuallyHurt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getDamageAfterMagicAbsorb(Lnet/minecraft/world/damagesource/DamageSource;F)F"
            )
    )
    private float acornlib$swordBlock(Player player, DamageSource source, float amount, @NotNull Operation<Float> original) {
        float base = original.call(player, source, amount);
        for (ItemStack stack : ItemUtils.getHeldStacks(player)) {
            if (player.isUsingItem() && ItemStack.matches(player.getUseItem(), stack)) {
                if (!this.level().isClientSide() && (!source.is(DamageTypeTags.BYPASSES_SHIELD) || source.is(DamageTypes.FALL)) && stack.getItem() instanceof AdvancedBlockingItem blockingItem) {
                    Vec3 damagePos = source.getSourcePosition();
                    if (source.is(DamageTypes.FALL)) {
                        if (MiscUtils.isLookingDown(player)) {
                            this.level().playSound(null, this, blockingItem.getBlockSound(), SoundSource.HOSTILE, 1.0F, 1.0F + this.level().getRandom().nextFloat() * 0.4F);
                            blockingItem.absorbDamage(player, source, stack, base / 2.0F);
                            return base / 4.0F;
                        }
                    } else if (damagePos != null) {
                        Vec3 rotVec = this.getViewVector(1.0F);

                        Vec3 difference = damagePos.vectorTo(this.getEyePosition()).normalize();
                        double angle = difference.dot(rotVec);
                        if (!(angle < -1.0F) && angle < -0.35) {
                            this.level().playSound(null, this, blockingItem.getBlockSound(), SoundSource.HOSTILE, 1.0F, 1.0F + this.level().getRandom().nextFloat() * 0.4F);
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
