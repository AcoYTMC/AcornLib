package net.acoyt.acornlib.impl.block;

import net.acoyt.acornlib.impl.util.interfaces.LangDiffering;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

/**
 * @author AcoYT
 */
@SuppressWarnings("unchecked")
public class PlushBlockItem extends BlockItem {
    public final int descColor;

    public PlushBlockItem(Block block, Properties properties, int descColor) {
        //? if > 1.21.1 {
        /*super(block, properties.overrideDescription(block.getDescriptionId()));
        *///? } else {
        super(block, properties);
        //? }
        this.descColor = descColor;
    }

    public PlushBlockItem(Block block, Properties properties) {
        this(block, properties, -1);
    }

    public Component getName(ItemStack stack) {
        if (this.getBlock() instanceof LangDiffering differing) {
            Optional<String> differed = (Optional<String>) differing.getDifferedKey(this.getBlock());
            if (differed.isPresent()) {
                return Component.translatable(differed.get());
            }
        }

        return super.getName(stack);
    }
}
