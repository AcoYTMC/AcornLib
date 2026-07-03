package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * @author AcoYT
 */
public interface SupporterFeaturesItem {
    /**
     * Checks If the Player is a General Supporter
     * @param player The Player to check
     * @return If the Player is a Supporter or not
     */
    default boolean isSupporter(Player player) {
        return AcornLib.isSupporter(player);
    }

    /**
     * Gets the Skin from the stack
     * @param stack The stack to check the skin of
     * @return The Skin string
     */
    default String getSkin(@NotNull ItemStack stack) {
        return stack.getOrDefault(AcornDataComponents.SKIN, "default");
    }

    /**
     * Sets the Skin for the stack
     * @param stack The stack to change the skin of
     * @param skin The string to set the Skin to
     */
    default void setSkin(@NotNull ItemStack stack, String skin) {
        stack.set(AcornDataComponents.SKIN, skin);
    }
}
