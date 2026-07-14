package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.effect.UnclearableEffect;
import net.acoyt.acornlib.api.item.AdvBurningItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectNoDieItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AcoYT
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract boolean addEffect(MobEffectInstance newEffect);

    public LivingEntityMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void acornlib$killConditionally(DamageSource killingDamage, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity)(Object)this;
        Entity attacker = killingDamage.getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            ItemStack stack = livingAttacker.getMainHandItem();
            if (stack.getItem() instanceof KillEffectNoDieItem killNoDie) {
                if (killNoDie.killEntity(livingAttacker.level(), stack, livingAttacker, living)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V"
            )
    )
    private void acornlib$igniteBeforeAttacking(ServerLevel level, DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getEntity();
        LivingEntity entity = (LivingEntity)(Object)this;
        if (attacker instanceof LivingEntity living && living.getMainHandItem().getItem() instanceof AdvBurningItem burningItem) {
            entity.igniteForSeconds(burningItem.getBurnTime(living.getMainHandItem(), living, entity));
        }
    }

    @WrapOperation(
            method = "hurtServer",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)V"
            )
    )
    private void acornlib$modifyAttackSource(LivingEntity instance, ServerLevel level, DamageSource source, float dmg, Operation<Void> original) {
        DamageSource damageSource = CustomKillSourceItem.isHolding(instance)
                ? ((CustomKillSourceItem) instance.getMainHandItem().getItem()).getKillSource(instance)
                : source;

        original.call(instance, level, damageSource, dmg);
    }

    /// Credit to Yak
    @WrapMethod(method = "removeAllEffects")
    private boolean acornlib$preventClear(Operation<Boolean> original) {
        LivingEntity living = (LivingEntity)(Object)this;
        if (!living.level().isClientSide()) {
            for (MobEffectInstance instance : living.getActiveEffectsMap().values()) {
                if (instance.getEffect().value() instanceof UnclearableEffect) {
                    boolean result = original.call();
                    this.addEffect(instance);
                    return result;
                }
            }
        }

        return original.call();
    }
}
