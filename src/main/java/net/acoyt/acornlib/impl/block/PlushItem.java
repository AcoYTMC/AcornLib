package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class PlushItem extends BlockItem {
    public PlushItem(Block block, Settings settings) {
        super(block, settings);
    }

    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> tooltip, TooltipType type) {
        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0x8D78CD));
        }

        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0xD54DAB));
        }

        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0x1B84C4));
        }

        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0x1d171d));
        }

        if (stack.isOf(AcornBlocks.TOAST_PLUSH.asItem())) {
            tooltip.accept(Text.translatable(this.getTranslationKey() + ".desc").formatted(Formatting.BOLD).withColor(0x852c24));
        }

    }
}
