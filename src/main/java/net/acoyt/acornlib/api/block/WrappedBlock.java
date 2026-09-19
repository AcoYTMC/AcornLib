package net.acoyt.acornlib.api.block;

//? if > 26.1.2 {

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Optional;
import java.util.function.Supplier;
//? }

/**
 * @author AcoYT
 */
//? if > 26.1.2 {
public class WrappedBlock<T extends Block> implements ItemLike, Supplier<T> {
    private final T block;
    private final BlockItemId itemId;

    public WrappedBlock(T block) {
        this.block = block;

        Optional<ResourceKey<Block>> blockKey = BuiltInRegistries.BLOCK.getResourceKey(block);
        Optional<ResourceKey<Item>> itemKey = BuiltInRegistries.ITEM.getResourceKey(asItem());

        if (blockKey.isPresent() && itemKey.isPresent()) {
            this.itemId = new BlockItemId(blockKey.get(), itemKey.get());
        } else {
            Identifier blockId = BuiltInRegistries.BLOCK.getKey(block);

            AcornLib.LOGGER.error("Could not get provided key for {}! Trying forced option.", blockId);
            this.itemId = new BlockItemId(
                    ResourceKey.create(Registries.BLOCK, blockId),
                    ResourceKey.create(Registries.ITEM, BuiltInRegistries.ITEM.getKey(asItem()))
            );
        }
    }

    public Item asItem() {
        return block.asItem();
    }

    public T get() {
        return block;
    }

    public BlockItemId itemId() {
        return itemId;
    }
}
//? }