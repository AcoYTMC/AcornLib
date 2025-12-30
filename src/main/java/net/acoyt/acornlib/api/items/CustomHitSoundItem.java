package net.acoyt.acornlib.api.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface CustomHitSoundItem {
    void playHitSound(PlayerEntity player, Entity target);
}
