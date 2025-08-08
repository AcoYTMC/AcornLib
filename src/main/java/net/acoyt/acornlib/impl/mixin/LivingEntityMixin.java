package net.acoyt.acornlib.impl.mixin;

import net.acoyt.acornlib.api.item.AdvBurningItem;
import net.acoyt.acornlib.api.item.KillEffectNoDieItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(
            method = "tryUseDeathProtector",
            at = @At("HEAD"),
            cancellable = true
    )
    private void killNoDie(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity)(Object)this;
        Entity attacker = source.getAttacker();
        if (attacker instanceof LivingEntity livingAttacker) {
            ItemStack stack = livingAttacker.getMainHandStack();
            if (stack.getItem() instanceof KillEffectNoDieItem killNoDie) {
                if (killNoDie.killEntity(livingAttacker.getWorld(), stack, livingAttacker, living)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void impaled$hellforkFix(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity attacker = source.getAttacker();
        LivingEntity entity = (LivingEntity)(Object)this;
        if (attacker instanceof LivingEntity living && living.getMainHandStack().getItem() instanceof AdvBurningItem burningItem) {
            entity.setOnFireFor(burningItem.getBurnTime(living));
        }
    }
}
