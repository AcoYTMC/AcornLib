package net.acoyt.acornlib.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

/**
 * @author AcoYT
 */
public interface CustomHitSoundItem {
    void playHitSound(PlayerEntity player, Entity target);
}
