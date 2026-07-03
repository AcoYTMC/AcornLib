package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * @author AcoYT
 */
public interface KillEffectItem {
    /**
     * @param level The user/attacker's level
     * @param stack The user/attacker's main hand stack
     * @param user The user/attacker
     * @param victim The entity being killed
     */
    void killEntity(Level level, ItemStack stack, LivingEntity user, LivingEntity victim);
}
