package net.acoyt.acornlib.impl.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.api.items.CustomHitParticleItem;
import net.acoyt.acornlib.api.items.CustomHitSoundItem;
import net.acoyt.acornlib.api.items.ShieldBreaker;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.impl.compat.AcornConfig;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.index.AcornComponents;
import net.acoyt.acornlib.impl.index.AcornCriterions;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

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

                int count = stack.get(AcornComponents.HIT_PARTICLE).count();

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
                    SoundEvent event = SoundEvent.of(stack.get(AcornComponents.HIT_SOUND).soundEvent());
                    if (event.id() != null) {
                        soundEvent = event;
                    }
                }

                boolean bl = stack.get(AcornComponents.HIT_SOUND).randomPitch();
                this.playSound(soundEvent, 1.0F, bl ? (float) (1.0F + player.getRandom().nextGaussian() / 10f) : 1.0F);
            }
        }
    }

    @Inject(
            method = "takeShieldHit",
            at = @At("HEAD")
    )
    private void silly(ServerWorld world, LivingEntity attacker, CallbackInfo ci) {
        ItemStack stack = attacker.getMainHandStack();
        ItemStack shield = this.getBlockingItem();
        BlocksAttacksComponent component = shield != null ? shield.get(DataComponentTypes.BLOCKS_ATTACKS) : null;
        if (stack.getItem() instanceof ShieldBreaker sb) {
            float cooldown = sb.shieldCooldown();
            if (cooldown > 0.0F && component != null) {
                component.applyShieldCooldown(world, this, cooldown, shield);
            }
        }

    }

    @Inject(
            method = {"attack"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"
            )}
    )
    private void grantCriticalHitCriterion(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player instanceof ServerPlayerEntity serverPlayer) {
            AcornCriterions.CRITICAL_HIT.trigger(serverPlayer);
        }
    }
}
