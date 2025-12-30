package net.acoyt.acornlib.api.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

public interface CustomHitParticleItem {
    void spawnHitParticles(PlayerEntity player, Entity target);
}
