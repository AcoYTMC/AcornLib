package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface KillEffectNoDieItem {
    /**
     * @param world the attacker/user's world
     * @param stack the ItemStack being used
     * @param user the entity attacking
     * @param victim the entity being killed
     * @return whether to save the victim or not
     */
    boolean killEntity(Level world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
