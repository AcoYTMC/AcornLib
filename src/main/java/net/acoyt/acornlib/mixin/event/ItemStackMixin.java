package net.acoyt.acornlib.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @WrapOperation(
            method = "getTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/Item;appendTooltip(Lnet/minecraft/item/ItemStack;" +
                            "Lnet/minecraft/item/Item$TooltipContext;" +
                            "Ljava/util/List;" +
                            "Lnet/minecraft/item/tooltip/TooltipType;)V"
            )
    )
    private void acornlib$betterTooltips(Item instance, ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, Operation<Void> original) {
        original.call(instance, stack, context, tooltip, type);
        BetterItemTooltipEvent.EVENT.invoker().getTooltip(stack, context, type, tooltip);
    }
}
