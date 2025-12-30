package net.acoyt.acornlib.impl.init.tag;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface AcornBlockTags {
    TagKey<Block> PLUSHIES = create("plushies");

    static TagKey<Block> create(String id) {
        return TagKey.of(RegistryKeys.BLOCK, AcornLib.id(id));
    }
}
