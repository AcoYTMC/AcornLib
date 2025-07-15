package net.acoyt.acornlib.item;

import net.acoyt.acornlib.AcornLib;
import net.minecraft.entity.player.PlayerEntity;

public interface SupporterFeaturesItem {
    /**
     * Checks If the Player is a General Supporter
     * @param player The Player to check
     * @return If the Player is a Supporter or not
     */
    default boolean isSupporter(PlayerEntity player) {
        return AcornLib.isSupporter(player);
    }
}
