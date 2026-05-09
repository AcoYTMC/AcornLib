package net.acoyt.acornlib.impl.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author AcoYT
 */
public interface AcornBlockTags {
    TagBuilder<Block> BLOCKS = new TagBuilder<>(AcornLib.MOD_ID, RegistryKeys.BLOCK);

    TagKey<Block> PLUSHIES = BLOCKS.register("plushies");
}
