package net.acoyt.acornlib.impl.block;

//? if < 1.21.3 {
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
//? }

/**
 * @author AcoYT
 */
//? if < 1.21.3 {
public class TranslationBlockItem extends BlockItem {
    public TranslationBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }
}
//? }