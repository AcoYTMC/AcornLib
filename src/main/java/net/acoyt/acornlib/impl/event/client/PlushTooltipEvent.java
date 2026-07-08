package net.acoyt.acornlib.impl.event.client;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.impl.block.PlushBlockItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

/**
 * @author AcoYT
 */
public class PlushTooltipEvent implements BetterItemTooltipEvent {
    public void getTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> lines) {
        if (stack.getItem() instanceof PlushBlockItem blockItem) {
            if (blockItem.descColor != -1) {
                lines.add(Component.translatable(blockItem.getDescriptionId() + ".desc").withStyle(ChatFormatting.BOLD).withColor(blockItem.descColor));
            }
        }
    }
}
