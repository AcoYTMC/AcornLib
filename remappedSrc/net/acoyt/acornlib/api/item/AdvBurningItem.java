package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface AdvBurningItem {
    int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim);
}
