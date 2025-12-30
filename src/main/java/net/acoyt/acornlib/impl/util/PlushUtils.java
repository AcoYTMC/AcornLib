package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornSounds;
import net.acoyt.acornlib.impl.index.AcornTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unused")
public class PlushUtils {
    // Plush Block from ItemStack
    public static Block getPlushBlock(ItemStack stack) {
        AtomicReference<Block> block = new AtomicReference<>(Blocks.OAK_PLANKS);

        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.ACO_PLUSH);
        }

        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.FESTIVE_ACO_PLUSH);
        }

        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            block.set(AcornBlocks.CLOWN_ACO_PLUSH);
        }

        if (stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem())) {
            block.set(AcornBlocks.MYTHORICAL_PLUSH);
        }

        if (stack.isOf(AcornBlocks.GNARP_PLUSH.asItem())) {
            block.set(AcornBlocks.GNARP_PLUSH);
        }

        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
            block.set(AcornBlocks.KIO_PLUSH);
        }

        if (stack.isOf(AcornBlocks.TOAST_PLUSH.asItem())) {
            block.set(AcornBlocks.TOAST_PLUSH);
        }

        if (stack.isOf(AcornBlocks.CHEM_PLUSH.asItem())) {
            block.set(AcornBlocks.CHEM_PLUSH);
        }

        if (stack.isIn(AcornTags.PLUSHIES)) {
            Identifier itemId = Registries.ITEM.getId(stack.getItem());
            return Registries.BLOCK.get(itemId); // Gets the block with the ID of the item, which *should* work.
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

        return block.getDefaultState().isIn(AcornTags.PLUSH_BLOCKS) ? block.asItem() : item.get(); // Gets the block as an item, if it's a plushie.
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

        return sounds.getOrDefault(state.getBlock(), SoundEvents.BLOCK_WOOL_HIT);
    }

    // Plush Sound from ItemStack
    public static SoundEvent getPlushSound(ItemStack stack) {
        SoundEvent squoze = SoundEvents.BLOCK_WOOL_HIT;

        Identifier itemId = Registries.ITEM.getId(stack.getItem());
        BlockState state = Registries.BLOCK.get(itemId).getDefaultState();
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
        AtomicReference<ItemStack> stack = new AtomicReference<>(Items.AIR.getDefaultStack());

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
        if (block.getDefaultState().isIn(AcornTags.PLUSH_BLOCKS)) {
            Identifier blockId = Registries.BLOCK.getId(block);
            return Registries.ITEM.get(blockId).getDefaultStack(); // Gets the item with the ID of the block, which *should* work.
        }

        ALib.plushies.forEach(plushData -> {
            if (block == plushData.block()) {
                Identifier blockId = Registries.BLOCK.getId(block);
                stack.set(Registries.ITEM.get(blockId).getDefaultStack());
            }
        });

        return stack.get();
    }
}