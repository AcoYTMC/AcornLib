package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CanEntityHealEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "heal", at = @At("HEAD"), cancellable = true)
    public void cancelHeal(float amount, CallbackInfo ci) {
        LivingEntity living = (LivingEntity)(Object)this;
        if (!CanEntityHealEvent.EVENT.invoker().canEntityHeal(living)) {
            ci.cancel();
        }
    }
}
