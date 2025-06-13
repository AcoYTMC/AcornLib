package net.acoyt.acornlib.item;

import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.entity.player.PlayerEntity;

public interface SupporterFeaturesItem {
    default boolean isSupporter(PlayerEntity player) {
        return AcornLibUtils.supporterUtils.isPlayerSupporter(player) || AcornLibUtils.supporterUtils.isPlayerFriend(player);
    }
}
