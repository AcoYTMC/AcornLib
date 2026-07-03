package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface CritEffectItem {
    void critEffect(Player player, ItemStack stack, Entity target);
}
