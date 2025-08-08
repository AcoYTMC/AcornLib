package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.acoyt.acornlib.impl.init.AcornSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

@SuppressWarnings("unused")
public class PlushUtils {
    // Plush Block from ItemStack
    public static Block getPlushBlock(ItemStack stack) {
        Block block = Blocks.OAK_PLANKS;

        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
            block = AcornBlocks.ACO_PLUSH;
        }

        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            block = AcornBlocks.FESTIVE_ACO_PLUSH;
        }

        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            block = AcornBlocks.CLOWN_ACO_PLUSH;
        }

        if (stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem())) {
            block = AcornBlocks.MYTHORICAL_PLUSH;
        }

        if (stack.isOf(AcornBlocks.GNARP_PLUSH.asItem())) {
            block = AcornBlocks.GNARP_PLUSH;
        }

        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
            block = AcornBlocks.KIO_PLUSH;
        }

        if (stack.isOf(AcornBlocks.TOAST_PLUSH.asItem())) {
            block = AcornBlocks.TOAST_PLUSH;
        }

        return block;
    }

    // Plush Item from Block
    public static Item getPlushItem(Block block) {
        Item item = Items.AIR;
        if (block == AcornBlocks.ACO_PLUSH) {
            item = AcornBlocks.ACO_PLUSH.asItem();
        }

        if (block == AcornBlocks.FESTIVE_ACO_PLUSH) {
            item = AcornBlocks.FESTIVE_ACO_PLUSH.asItem();
        }

        if (block == AcornBlocks.CLOWN_ACO_PLUSH) {
            item = AcornBlocks.CLOWN_ACO_PLUSH.asItem();
        }

        if (block == AcornBlocks.MYTHORICAL_PLUSH) {
            item = AcornBlocks.MYTHORICAL_PLUSH.asItem();
        }

        if (block == AcornBlocks.GNARP_PLUSH) {
            item = AcornBlocks.GNARP_PLUSH.asItem();
        }

        if (block == AcornBlocks.KIO_PLUSH) {
            item = AcornBlocks.KIO_PLUSH.asItem();
        }

        if (block == AcornBlocks.TOAST_PLUSH) {
            item = AcornBlocks.TOAST_PLUSH.asItem();
        }

        return item;
    }

    // Plush Sound from BlockState
    public static SoundEvent getPlushSound(BlockState state) {
        SoundEvent squoze = SoundEvents.BLOCK_WOOL_HIT;
        if (state.getBlock() == AcornBlocks.ACO_PLUSH) {
            squoze = AcornSounds.ACO_PLUSH_HONK;
        }

        if (state.getBlock() == AcornBlocks.FESTIVE_ACO_PLUSH) {
            squoze = AcornSounds.FESTIVE_ACO_PLUSH_HONK;
        }

        if (state.getBlock() == AcornBlocks.CLOWN_ACO_PLUSH) {
            squoze = AcornSounds.CLOWN_ACO_PLUSH_HONK;
        }

        if (state.getBlock() == AcornBlocks.MYTHORICAL_PLUSH) {
            squoze = AcornSounds.MYTH_PLUSH_HONK;
        }

        if (state.getBlock() == AcornBlocks.GNARP_PLUSH) {
            squoze = AcornSounds.HOLY_GNARP;
        }

        if (state.getBlock() == AcornBlocks.KIO_PLUSH) {
            squoze = AcornSounds.FOUR_KIO;
        }

        if (state.getBlock() == AcornBlocks.TOAST_PLUSH) {
            squoze = AcornSounds.MREW;
        }

        return squoze;
    }

    // Plush Sound from ItemStack
    public static SoundEvent getPlushSound(ItemStack stack) {
        SoundEvent squoze = SoundEvents.BLOCK_WOOL_HIT;
        if (stack.isOf(AcornBlocks.ACO_PLUSH.asItem())) {
            squoze = AcornSounds.ACO_PLUSH_HONK;
        }

        if (stack.isOf(AcornBlocks.FESTIVE_ACO_PLUSH.asItem())) {
            squoze = AcornSounds.FESTIVE_ACO_PLUSH_HONK;
        }

        if (stack.isOf(AcornBlocks.CLOWN_ACO_PLUSH.asItem())) {
            squoze = AcornSounds.CLOWN_ACO_PLUSH_HONK;
        }

        if (stack.isOf(AcornBlocks.MYTHORICAL_PLUSH.asItem())) {
            squoze = AcornSounds.MYTH_PLUSH_HONK;
        }

        if (stack.isOf(AcornBlocks.GNARP_PLUSH.asItem())) {
            squoze = AcornSounds.HOLY_GNARP;
        }

        if (stack.isOf(AcornBlocks.KIO_PLUSH.asItem())) {
            squoze = AcornSounds.FOUR_KIO;
        }

        if (stack.isOf(AcornBlocks.TOAST_PLUSH.asItem())) {
            squoze = AcornSounds.MREW;
        }

        return squoze;
    }

    // Plush Stack from Block
    public static ItemStack getPlushStack(Block block) {
        ItemStack stack = Items.STICKY_PISTON.getDefaultStack();

        if (block == AcornBlocks.ACO_PLUSH) {
            stack = AcornBlocks.ACO_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.FESTIVE_ACO_PLUSH) {
            stack = AcornBlocks.FESTIVE_ACO_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.CLOWN_ACO_PLUSH) {
            stack = AcornBlocks.CLOWN_ACO_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.MYTHORICAL_PLUSH) {
            stack = AcornBlocks.MYTHORICAL_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.GNARP_PLUSH) {
            stack = AcornBlocks.GNARP_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.KIO_PLUSH) {
            stack = AcornBlocks.KIO_PLUSH.asItem().getDefaultStack();
        }

        if (block == AcornBlocks.TOAST_PLUSH) {
            stack = AcornBlocks.TOAST_PLUSH.asItem().getDefaultStack();
        }

        return stack;
    }
}