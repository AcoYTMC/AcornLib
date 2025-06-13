package net.acoyt.acornlib.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface KillEffectItem {
    void killEntity(World world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
