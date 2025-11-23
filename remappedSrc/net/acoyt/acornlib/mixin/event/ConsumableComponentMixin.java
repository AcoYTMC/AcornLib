package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CanConsumeItemEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Consumable.class)
public class ConsumableComponentMixin {
    @Inject(method = "canConsume", at = @At("HEAD"), cancellable = true)
    private void gnarpian$canConsume(LivingEntity user, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Boolean original = cir.getReturnValue();
        if (original != null && user instanceof Player player) {
            boolean bl = CanConsumeItemEvent.EVENT.invoker().canConsume(player, stack);
            cir.setReturnValue(bl && original);
        }
    }
}
