package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface SupporterFeaturesItem {
    /**
     * Checks If the Player is a General Supporter
     * @param player The Player to check
     * @return If the Player is a Supporter or not
     */
    default boolean isSupporter(PlayerEntity player) {
        return AcornLib.isSupporter(player);
    }

    /**
     * Gets the Skin from the stack
     * @param stack The stack to check the skin of
     * @return The Skin string
     */
    default String getSkin(@NotNull ItemStack stack) {
        return stack.get(AcornComponents.SKIN);
    }

    /**
     * Sets the Skin for the stack
     * @param stack The stack to change the skin of
     * @param skin The string to set the Skin to
     */
    default void setSkin(@NotNull ItemStack stack, String skin) {
        stack.set(AcornComponents.SKIN, skin);
    }
}
