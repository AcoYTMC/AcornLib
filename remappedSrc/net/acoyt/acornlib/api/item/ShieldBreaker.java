package net.acoyt.acornlib.api.item;

import net.minecraft.world.item.ItemStack;

public interface ShieldBreaker {
    float shieldCooldown(ItemStack stack);
}
