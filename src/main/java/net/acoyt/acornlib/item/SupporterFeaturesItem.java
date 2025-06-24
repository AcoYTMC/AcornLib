package net.acoyt.acornlib.item;

import net.acoyt.acornlib.util.supporter.SupporterUtils;
import net.minecraft.entity.player.PlayerEntity;

public interface SupporterFeaturesItem {
    default boolean isSupporter(PlayerEntity player) {
        return SupporterUtils.isPlayerSupporter(player) || SupporterUtils.isPlayerFriend(player);
    }
}
