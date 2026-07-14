package net.acoyt.acornlib.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @WrapOperation(
            method = "getTooltipLines",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;addDetailsToTooltip(Lnet/minecraft/world/item/Item$TooltipContext;Lnet/minecraft/world/item/component/TooltipDisplay;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/TooltipFlag;Ljava/util/function/Consumer;)V"
            )
    )
    private void acornlib$betterTooltips(ItemStack instance, Item.TooltipContext context, TooltipDisplay display, @Nullable Player player, TooltipFlag tooltipFlag, Consumer<Component> builder, Operation<Void> original) {
        BetterItemTooltipEvent.EVENT.invoker().getTooltip(instance, context, tooltipFlag, builder);
        original.call(instance, context, display, player, tooltipFlag, builder);
    }
}
