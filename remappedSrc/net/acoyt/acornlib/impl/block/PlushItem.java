package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import java.util.function.Consumer;

public class PlushItem extends BlockItem {
    private final int descColor;

    public PlushItem(Block block, Properties settings, int descColor) {
        super(block, settings);

        this.descColor = descColor;
    }

    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> tooltip, TooltipFlag type) {
        if (!stack.is(AcornBlocks.MYTHORICAL_PLUSH.asItem()) && !stack.is(AcornBlocks.GNARP_PLUSH.asItem()))
            tooltip.accept(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.BOLD).withColor(this.descColor));
    }
}
