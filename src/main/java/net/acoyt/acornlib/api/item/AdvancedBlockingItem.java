package net.acoyt.acornlib.api.item;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;

/**
 * @author AcoYT
 */
public interface AdvancedBlockingItem {
    SoundEvent getBlockSound();

    void absorbDamage(PlayerEntity player, DamageSource source, ItemStack stack, float base);
}
