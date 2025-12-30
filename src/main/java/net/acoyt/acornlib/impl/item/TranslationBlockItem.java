package net.acoyt.acornlib.impl.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class TranslationBlockItem extends BlockItem {
    public TranslationBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getBlock().getTranslationKey();
    }
}
