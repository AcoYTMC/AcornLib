package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface CustomHitSoundItem {
    void playHitSound(Player player, Entity target);
}
