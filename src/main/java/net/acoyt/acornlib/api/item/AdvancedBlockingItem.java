package net.acoyt.acornlib.api.item;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;

public interface AdvancedBlockingItem {
    SoundEvent blockSound();

    void absorbDamage(PlayerEntity player, DamageSource source, ItemStack stack, float base);
}
