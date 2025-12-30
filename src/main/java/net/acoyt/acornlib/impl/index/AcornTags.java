package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public interface AcornTags {
    TagKey<Item> PLUSHIES = itemOf("plushies");
    TagKey<Block> PLUSH_BLOCKS = blockOf("plushies");

    static TagKey<Item> itemOf(String id) {
        return TagKey.of(RegistryKeys.ITEM, AcornLib.id(id));
    }

    static TagKey<Block> blockOf(String id) {
        return TagKey.of(RegistryKeys.BLOCK, AcornLib.id(id));
    }
}
