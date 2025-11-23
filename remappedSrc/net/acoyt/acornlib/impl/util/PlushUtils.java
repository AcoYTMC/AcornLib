package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.acoyt.acornlib.impl.init.AcornSounds;
import net.acoyt.acornlib.impl.init.AcornTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PlushUtils {
    // Plush Block from ItemStack
    public static Block getPlushBlock(ItemStack stack) {
        AtomicReference<Block> block = new AtomicReference<>(Blocks.OAK_PLANKS);

        if (stack.is(AcornBlocks.ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.ACO_PLUSH);
        }

        if (stack.is(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.FESTIVE_ACO_PLUSH);
        }

        if (stack.is(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.CLOWN_ACO_PLUSH);
        }

        if (stack.is(AcornBlocks.MYTHORICAL_PLUSH.asItem())) {
            block.set(AcornBlocks.MYTHORICAL_PLUSH);
        }

        if (stack.is(AcornBlocks.GNARP_PLUSH.asItem())) {
            block.set(AcornBlocks.GNARP_PLUSH);
        }

        if (stack.is(AcornBlocks.KIO_PLUSH.asItem())) {
            block.set(AcornBlocks.KIO_PLUSH);
        }

        if (stack.is(AcornBlocks.TOAST_PLUSH.asItem())) {
            block.set(AcornBlocks.TOAST_PLUSH);
        }

        if (stack.is(AcornBlocks.CHEM_PLUSH.asItem())) {
            block.set(AcornBlocks.CHEM_PLUSH);
        }

        if (stack.is(AcornTags.PLUSHIES)) {
            ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
            return BuiltInRegistries.BLOCK.getValue(itemId); // Gets the block with the ID of the item, which *should* work.
        }

        ALib.plushies.forEach(plushData -> {
            if (plushData.block().asItem().equals(stack.getItem())) {
                block.set(plushData.block());
            }
        });

        return block.get();
    }

    // Plush Item from Block
    public static Item getPlushItem(Block block) {
        AtomicReference<Item> item = new AtomicReference<>(Items.AIR);
        if (block == AcornBlocks.ACO_PLUSH) {
            item.set(AcornBlocks.ACO_PLUSH.asItem());
        }

        if (block == AcornBlocks.FESTIVE_ACO_PLUSH) {
            item.set(AcornBlocks.FESTIVE_ACO_PLUSH.asItem());
        }

        if (block == AcornBlocks.CLOWN_ACO_PLUSH) {
            item.set(AcornBlocks.CLOWN_ACO_PLUSH.asItem());
        }

        if (block == AcornBlocks.MYTHORICAL_PLUSH) {
            item.set(AcornBlocks.MYTHORICAL_PLUSH.asItem());
        }

        if (block == AcornBlocks.GNARP_PLUSH) {
            item.set(AcornBlocks.GNARP_PLUSH.asItem());
        }

        if (block == AcornBlocks.KIO_PLUSH) {
            item.set(AcornBlocks.KIO_PLUSH.asItem());
        }

        if (block == AcornBlocks.TOAST_PLUSH) {
            item.set(AcornBlocks.TOAST_PLUSH.asItem());
        }

        if (block == AcornBlocks.CHEM_PLUSH) {
            item.set(AcornBlocks.CHEM_PLUSH.asItem());
        }

        ALib.plushies.forEach(plushData -> {
            if (block == plushData.block()) {
                item.set(plushData.block().asItem());
            }
        });

        return block.defaultBlockState().is(AcornTags.PLUSH_BLOCKS) ? block.asItem() : item.get(); // Gets the block as an item, if it's a plushie.
    }

    // Plush Sound from BlockState
    public static SoundEvent getPlushSound(BlockState state) {
        Map<Block, SoundEvent> sounds = new HashMap<>();

        sounds.put(AcornBlocks.ACO_PLUSH, AcornSounds.ACO_PLUSH_HONK);
        sounds.put(AcornBlocks.FESTIVE_ACO_PLUSH, AcornSounds.FESTIVE_ACO_PLUSH_HONK);
        sounds.put(AcornBlocks.CLOWN_ACO_PLUSH, AcornSounds.CLOWN_ACO_PLUSH_HONK);
        sounds.put(AcornBlocks.MYTHORICAL_PLUSH, AcornSounds.MYTH_PLUSH_HONK);
        sounds.put(AcornBlocks.GNARP_PLUSH, AcornSounds.HOLY_GNARP);
        sounds.put(AcornBlocks.KIO_PLUSH, AcornSounds.FOUR_KIO);
        sounds.put(AcornBlocks.TOAST_PLUSH, AcornSounds.MREW);
        sounds.put(AcornBlocks.CHEM_PLUSH, AcornSounds.GOOBER);

        ALib.plushies.forEach(plushData -> sounds.put(plushData.block(), plushData.soundEvent()));

        return sounds.getOrDefault(state.getBlock(), SoundEvents.WOOL_HIT);
    }

    // Plush Sound from ItemStack
    public static SoundEvent getPlushSound(ItemStack stack) {
        SoundEvent squoze = SoundEvents.WOOL_HIT;

        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        BlockState state = BuiltInRegistries.BLOCK.getValue(itemId).defaultBlockState();
        return getPlushSound(state);

//        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
//            squoze = AcornSounds.ACO_PLUSH_HONK;
//        }
//
//        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
//            squoze = AcornSounds.FESTIVE_ACO_PLUSH_HONK;
//        }
//
//        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
//            squoze = AcornSounds.CLOWN_ACO_PLUSH_HONK;
//        }
//
//        if (stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem())) {
//            squoze = AcornSounds.MYTH_PLUSH_HONK;
//        }
//
//        if (stack.isOf(AcornBlocks.GNARP_PLUSH.asItem())) {
//            squoze = AcornSounds.HOLY_GNARP;
//        }
//
//        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
//            squoze = AcornSounds.FOUR_KIO;
//        }
//
//        if (stack.isOf(AcornBlocks.TOAST_PLUSH.asItem())) {
//            squoze = AcornSounds.MREW;
//        }
//
//        return squoze;
    }

    // Plush Stack from Block
    public static ItemStack getPlushStack(Block block) {
        AtomicReference<ItemStack> stack = new AtomicReference<>(Items.AIR.getDefaultInstance());

//        if (block == AcornBlocks.ACO_PLUSH) {
//            stack = AcornBlocks.ACO_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.FESTIVE_ACO_PLUSH) {
//            stack = AcornBlocks.FESTIVE_ACO_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.CLOWN_ACO_PLUSH) {
//            stack = AcornBlocks.CLOWN_ACO_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.MYTHORICAL_PLUSH) {
//            stack = AcornBlocks.MYTHORICAL_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.GNARP_PLUSH) {
//            stack = AcornBlocks.GNARP_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.KIO_PLUSH) {
//            stack = AcornBlocks.KIO_PLUSH.asItem().getDefaultStack();
//        }
//
//        if (block == AcornBlocks.TOAST_PLUSH) {
//            stack = AcornBlocks.TOAST_PLUSH.asItem().getDefaultStack();
//        }
//
        // Checks if it's a plush block
        if (block.defaultBlockState().is(AcornTags.PLUSH_BLOCKS)) {
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
            return BuiltInRegistries.ITEM.getValue(blockId).getDefaultInstance(); // Gets the item with the ID of the block, which *should* work.
        }

        ALib.plushies.forEach(plushData -> {
            if (block == plushData.block()) {
                ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
                stack.set(BuiltInRegistries.ITEM.getValue(blockId).getDefaultInstance());
            }
        });

        return stack.get();
    }
}