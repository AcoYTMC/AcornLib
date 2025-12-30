package net.acoyt.acornlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.List;

public class GatheringUtils {
    public static List<Item> getItemsInTag(TagKey<Item> tagKey) {
        List<Item> items = new ArrayList<>();

        for (Item item : getAll(Registries.ITEM)) {
            if (item.getDefaultStack().isIn(tagKey)) {
                items.add(item);
            }
        }

        return items;
    }

    public static List<Block> getBlocksInTag(TagKey<Block> tagKey) {
        List<Block> blocks = new ArrayList<>();

        for (Block block : getAll(Registries.BLOCK)) {
            if (block.getDefaultState().isIn(tagKey)) {
                blocks.add(block);
            }
        }

        return blocks;
    }

    public static <T> List<T> getAll(Registry<T> registry) {
        List<T> list = new ArrayList<>();

        for (int i = 0; i < registry.size(); i++) {
            list.add(registry.get(i));
        }

        return list;
    }
}
