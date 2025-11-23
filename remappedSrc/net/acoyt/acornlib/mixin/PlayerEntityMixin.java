package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.acoyt.acornlib.api.util.ParticleUtils;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.init.AcornCriterions;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Debug(export = true)
@Mixin(Player.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyReturnValue(
            method = "getDisplayName",
            at = @At("RETURN")
    )
    public Component acornLib$applyFriendFormattingToName(Component original) {
        return AcornConfig.allowSupporterNameColors ? AcornLibUtils.stylizeNames(this.getUUID(), original) : original;
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;getAttackCooldownProgress(F)F"
            )
    )
    private void acornlib$spawnCustomHitParticlesAndPlayCustomHitSound(Entity target, CallbackInfo ci) {
        Player player = (Player)(Object)this;
        if (this.getAttackCooldownProgress(0.5F) > 0.9F) {
            Item var4 = this.getMainHandItem().getItem();
            if (var4 instanceof CustomHitParticleItem hitParticleItem) {
                hitParticleItem.spawnHitParticles(player, target);
            }

            var4 = this.getMainHandItem().getItem();
            if (var4 instanceof CustomHitSoundItem hitSoundItem) {
                hitSoundItem.playHitSound(player, target);
            }
        }

        if (this.getAttackCooldownProgress(0.5F) > 0.9F) {
            ItemStack stack = this.getMainHandItem();
            if (stack.has(AcornComponents.HIT_PARTICLE)) {
                ParticleOptions par = ParticleTypes.SWEEP_ATTACK;
                if (stack.get(AcornComponents.HIT_PARTICLE) != null) {
                    SimpleParticleType reg = (SimpleParticleType)BuiltInRegistries.PARTICLE_TYPE.getValue(Objects.requireNonNull(stack.get(AcornComponents.HIT_PARTICLE)).particle());
                    if (reg != null) {
                        par = reg;
                    }
                }

                int count = stack.getOrDefault(AcornComponents.HIT_PARTICLE, HitParticleComponent.DEFAULT).count();

                ParticleUtils.spawnSweepParticles(par, count, player);
            }

            if (stack.has(AcornComponents.SWEEP_PARTICLE)) {
                SweepParticleComponent advHitParticle = stack.get(AcornComponents.SWEEP_PARTICLE);
                assert advHitParticle != null;
                int base = advHitParticle.baseColor();
                int shadow = advHitParticle.shadowColor();

                ParticleUtils.spawnSweepParticles(new SweepParticleEffect(base, shadow), player);
            }

            if (stack.has(AcornComponents.HIT_SOUND)) {
                SoundEvent soundEvent = SoundEvents.EMPTY;
                if (stack.get(AcornComponents.HIT_SOUND) != null) {
                    SoundEvent event = SoundEvent.createVariableRangeEvent(stack.getOrDefault(AcornComponents.HIT_SOUND, HitSoundComponent.DEFAULT).soundEvent());
                    if (event.location() != null) {
                        soundEvent = event;
                    }
                }

                boolean bl = stack.getOrDefault(AcornComponents.HIT_SOUND, HitSoundComponent.DEFAULT).randomPitch();
                this.playSound(soundEvent, 1.0F, bl ? (float) (1.0F + player.getRandom().nextGaussian() / 10f) : 1.0F);
            }
        }
    }

    @Inject(
            method = "takeShieldHit",
            at = @At("HEAD")
    )
    private void silly(ServerLevel world, LivingEntity attacker, CallbackInfo ci) {
        ItemStack stack = attacker.getMainHandItem();
        ItemStack shield = this.getItemBlockingWith();
        BlocksAttacks component = shield != null ? shield.get(DataComponents.BLOCKS_ATTACKS) : null;
        if (stack.getItem() instanceof ShieldBreaker sb) {
            float cooldown = sb.shieldCooldown(stack);
            if (cooldown > 0.0F && component != null) {
                component.disable(world, this, cooldown, shield);
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
        Player player = (Player)(Object)this;
        if (player instanceof ServerPlayer serverPlayer) {
            AcornCriterions.CRITICAL_HIT.trigger(serverPlayer);
        }
    }
}
