package net.acoyt.acornlib.impl.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

/**
 * @author AcoYT
 */
public interface AcornItemTags {
    TagBuilder<Item> ITEMS = new TagBuilder<>(AcornLib.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> PLUSHIES = ITEMS.register("plushies");
}
