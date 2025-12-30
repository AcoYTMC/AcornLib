package net.acoyt.acornlib.api.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface AdvBurningItem {
    int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim);
}
