package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface KillEffectItem {
    /**
     * @param world The user/attacker's world
     * @param stack The user/attacker's main hand stack
     * @param user The user/attacker
     * @param victim The entity being killed
     */
    void killEntity(Level world, ItemStack stack, LivingEntity user, LivingEntity victim);
}
