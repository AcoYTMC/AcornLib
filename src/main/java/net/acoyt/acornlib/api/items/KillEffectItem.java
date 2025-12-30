package net.acoyt.acornlib.api.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface KillEffectItem {
    /**
     * @param world The user/attacker's world
     * @param stack The user/attacker's main hand stack
     * @param user The user/attacker
     * @param victim The entity being killed
     */
    void killEntity(World world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
