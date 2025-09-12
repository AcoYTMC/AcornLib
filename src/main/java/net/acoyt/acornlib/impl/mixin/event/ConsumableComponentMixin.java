package net.acoyt.acornlib.impl.mixin.event;

import net.acoyt.acornlib.api.event.CanConsumeItemEvent;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConsumableComponent.class)
public class ConsumableComponentMixin {
    @Inject(method = "canConsume", at = @At("HEAD"), cancellable = true)
    private void gnarpian$canConsume(LivingEntity user, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean original = cir.getReturnValue();
        if (user instanceof PlayerEntity player) {
            boolean bl = CanConsumeItemEvent.EVENT.invoker().canConsume(player, stack);
            cir.setReturnValue(bl && original);
        }
    }
}
