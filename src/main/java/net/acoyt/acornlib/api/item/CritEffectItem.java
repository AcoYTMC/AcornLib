package net.acoyt.acornlib.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

/**
 * @author AcoYT
 */
public interface CritEffectItem {
    void critEffect(PlayerEntity player, ItemStack stack, Entity target);
}
