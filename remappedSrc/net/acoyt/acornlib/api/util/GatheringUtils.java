package net.acoyt.acornlib.api.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GatheringUtils {
    public static List<Item> getItemsInTag(TagKey<Item> tagKey) {
        List<Item> items = new ArrayList<>();

        for (Item item : getAll(BuiltInRegistries.ITEM)) {
            if (item.getDefaultInstance().is(tagKey)) {
                items.add(item);
            }
        }

        return items;
    }

    public static List<Block> getBlocksInTag(TagKey<Block> tagKey) {
        List<Block> blocks = new ArrayList<>();

        for (Block block : getAll(BuiltInRegistries.BLOCK)) {
            if (block.defaultBlockState().is(tagKey)) {
                blocks.add(block);
            }
        }

        return blocks;
    }

    public static <T> List<T> getAll(Registry<T> registry) {
        List<T> list = new ArrayList<>();

        for (int i = 0; i < registry.size(); i++) {
            list.add(registry.byId(i));
        }

        return list;
    }
}
