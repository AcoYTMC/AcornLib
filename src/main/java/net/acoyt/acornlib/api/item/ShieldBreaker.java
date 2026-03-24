package net.acoyt.acornlib.api.item;

import net.minecraft.item.ItemStack;

public interface ShieldBreaker {
    int getShieldCooldown(ItemStack stack);
}
