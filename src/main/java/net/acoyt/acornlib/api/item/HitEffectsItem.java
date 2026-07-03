package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

/**
 * @author AcoYT
 */
public interface HitEffectsItem {
    void runHitEffects(Player player, Entity target);
}
