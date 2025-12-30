package net.acoyt.acornlib.api.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface KillEffectNoDieItem {
    /**
     * @param world the attacker/user's world
     * @param stack the ItemStack being used
     * @param user the entity attacking
     * @param victim the entity being killed
     * @return whether to save the victim or not
     */
    boolean killEntity(World world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
