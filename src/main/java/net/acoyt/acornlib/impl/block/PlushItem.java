package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class PlushItem extends BlockItem {
    private final int descColor;

    public PlushItem(Block block, Settings settings, int descColor) {
        super(block, settings);

        this.descColor = descColor;
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getBlock().getTranslationKey();
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem()) && !stack.isOf(AcornBlocks.GNARP_PLUSH.asItem()))
            tooltip.add(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(this.descColor));
    }
}
