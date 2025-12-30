package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class PlushItem extends BlockItem {
    private final int descColor;

    public PlushItem(Block block, Settings settings, int descColor) {
        super(block, settings);

        this.descColor = descColor;
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type) {
        if (!stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem()) && !stack.isOf(AcornBlocks.GNARP_PLUSH.asItem()))
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(this.descColor));
    }
}
