package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.cca.HoldingComponent;
import net.acoyt.acornlib.init.AcornComponents;
import net.acoyt.acornlib.item.CustomHitParticleItem;
import net.acoyt.acornlib.item.CustomHitSoundItem;
import net.acoyt.acornlib.item.ShieldBreaker;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.acoyt.acornlib.util.interfaces.AcornPlayerEntity;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements AcornPlayerEntity {
    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyReturnValue(
            method = "getDisplayName",
            at = @At("RETURN")
    )
    public Text acornLib$applyFriendFormattingToName(Text original) {
        return AcornLibUtils.stylizeNames(this.getUuid(), original);
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
                hitParticleItem.spawnHitParticles(player);
            }

            var4 = this.getMainHandStack().getItem();
            if (var4 instanceof CustomHitSoundItem hitSoundItem) {
                hitSoundItem.playHitSound(player);
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

                double deltaX = -MathHelper.sin((float)((double)player.getYaw() * (Math.PI / 180D)));
                double deltaZ = MathHelper.cos((float)((double)player.getYaw() * (Math.PI / 180D)));
                World var7 = player.getWorld();
                if (var7 instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(par, player.getX() + deltaX, player.getBodyY(0.5F), player.getZ() + deltaZ, 0, deltaX, 0.0F, deltaZ, 0.0F);
                }
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

    // Sillies
    @Override
    public boolean acornlib$holdingAttack() {
        return HoldingComponent.get((PlayerEntity)(Object)this).isAttacking();
    }

    @Override
    public void acornlib$setHoldingAttack(boolean attackHeld) {
        HoldingComponent.get((PlayerEntity)(Object)this).setAttacking(attackHeld);
    }

    @Override
    public int acornlib$getHoldingAttackTime() {
        return HoldingComponent.get((PlayerEntity)(Object)this).getAttackTicks();
    }

    @Override
    public boolean acornlib$holdingUse() {
        return HoldingComponent.get((PlayerEntity)(Object)this).isUsing();
    }

    @Override
    public void acornlib$setHoldingUse(boolean useHeld) {
        HoldingComponent.get((PlayerEntity)(Object)this).setUsing(useHeld);
    }

    @Override
    public int acornlib$getHoldingUseTime() {
        return HoldingComponent.get((PlayerEntity)(Object)this).getUsageTicks();
    }
}
