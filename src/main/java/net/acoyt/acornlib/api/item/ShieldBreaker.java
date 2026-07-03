package net.acoyt.acornlib.api.item;

import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface ShieldBreaker {
    float getShieldCooldown(ItemStack stack);
}
