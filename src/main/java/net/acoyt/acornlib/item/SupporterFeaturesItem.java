package net.acoyt.acornlib.item;

import net.acoyt.acornlib.AcornLib;
import net.minecraft.entity.player.PlayerEntity;

public interface SupporterFeaturesItem {
    default boolean isSupporter(PlayerEntity player) {
        return AcornLib.isSupporter(player);
    }
}
