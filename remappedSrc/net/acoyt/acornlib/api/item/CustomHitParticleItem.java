package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public interface CustomHitParticleItem {
    void spawnHitParticles(Player player, Entity target);
}
