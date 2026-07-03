package net.acoyt.acornlib.api.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface AdvancedBlockingItem {
    SoundEvent getBlockSound();

    void absorbDamage(Player player, DamageSource source, ItemStack stack, float base);
}
