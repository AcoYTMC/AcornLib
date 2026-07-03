package net.acoyt.acornlib.impl.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

/**
 * @author AcoYT
 */
public interface AcornItemTags {
    TagBuilder<Item> ITEMS = new TagBuilder<>(AcornLib.MOD_ID, Registries.ITEM);

    TagKey<Item> PLUSHIES = ITEMS.register("plushies");
}
