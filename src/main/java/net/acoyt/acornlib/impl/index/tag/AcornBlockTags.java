package net.acoyt.acornlib.impl.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * @author AcoYT
 */
public interface AcornBlockTags {
    TagBuilder<Block> BLOCKS = new TagBuilder<>(AcornLib.MOD_ID, Registries.BLOCK);

    TagKey<Block> PLUSHIES = BLOCKS.register("plushies");
}
