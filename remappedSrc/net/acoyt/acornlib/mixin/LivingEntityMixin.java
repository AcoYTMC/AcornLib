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

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    public abstract boolean addStatusEffect(MobEffectInstance effect);

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(
            method = "tryUseDeathProtector",
            at = @At("HEAD"),
            cancellable = true
    )
    private void killNoDie(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity)(Object)this;
        Entity attacker = source.getEntity();
        if (attacker instanceof LivingEntity livingAttacker) {
            ItemStack stack = livingAttacker.getMainHandItem();
            if (stack.getItem() instanceof KillEffectNoDieItem killNoDie) {
                if (killNoDie.killEntity(livingAttacker.level(), stack, livingAttacker, living)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void impaled$hellforkFix(ServerLevel world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getEntity();
        LivingEntity entity = (LivingEntity)(Object)this;
        if (attacker instanceof LivingEntity living && living.getMainHandItem().getItem() instanceof AdvBurningItem burningItem) {
            entity.igniteForSeconds(burningItem.getBurnTime(living.getMainHandItem(), living, entity));
        }
    }

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void silly(LivingEntity instance, ServerLevel world, DamageSource source, float amount, Operation<Void> original) {
        if (source.getEntity() instanceof LivingEntity living && living.getMainHandItem().getItem() instanceof CustomKillSourceItem killSource) {
            original.call(instance, world, killSource.getKillSource(instance), amount);
        } else {
            original.call(instance, world, source, amount);
        }
    }

    @WrapMethod(method = "clearStatusEffects")
    private boolean preventClear(Operation<Boolean> original) {
        LivingEntity living = (LivingEntity)(Object)this;
        if (!living.level().isClientSide()) {
            for (MobEffectInstance instance : living.getActiveEffectsMap().values()) {
                if (instance.getEffect().value() instanceof UnclearableEffect) {
                    boolean result = original.call();
                    this.addStatusEffect(instance);
                    return result;
                }
            }
        }
        return original.call();
    }
}
