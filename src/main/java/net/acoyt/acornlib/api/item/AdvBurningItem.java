package net.acoyt.acornlib.api.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface AdvBurningItem {
    //~ if > 1.21.1 'float' -> 'int'
    int getBurnTime(ItemStack stack, LivingEntity attacker, LivingEntity victim);
}
