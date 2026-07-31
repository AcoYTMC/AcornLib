package net.acoyt.acornlib.api.item;

import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface ShieldBreaker {
    //~ if > 1.21.1 'int' -> 'float'
    float getShieldCooldown(ItemStack stack);
}
