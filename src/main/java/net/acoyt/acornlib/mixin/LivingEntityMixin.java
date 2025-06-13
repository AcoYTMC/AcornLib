package net.acoyt.acornlib.mixin;

import net.acoyt.acornlib.item.KillEffectNoDieItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
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
                killNoDie.killEntity(livingAttacker.getWorld(), stack, livingAttacker, living);
                cir.setReturnValue(true);
            }
        }
    }
}
