package net.acoyt.acornlib.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface KillEffectNoDieItem {
    void killEntity(World world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
