package net.acoyt.acornlib.impl.index.tag;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author AcoYT
 */
public interface AcornItemTags {
    TagKey<Item> PLUSHIES = create("plushies");

    static TagKey<Item> create(String id) {
        return TagKey.of(RegistryKeys.ITEM, AcornLib.id(id));
    }
}
