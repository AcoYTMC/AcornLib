package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface AcornTags {
    TagKey<Item> PLUSHIES = itemOf("plushies");
    TagKey<Block> PLUSH_BLOCKS = blockOf("plushies");

    static TagKey<Item> itemOf(String id) {
        return TagKey.create(Registries.ITEM, AcornLib.id(id));
    }

    static TagKey<Block> blockOf(String id) {
        return TagKey.create(Registries.BLOCK, AcornLib.id(id));
    }
}
