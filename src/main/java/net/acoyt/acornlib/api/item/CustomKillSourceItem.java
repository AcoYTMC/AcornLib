package net.acoyt.acornlib.api.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author AcoYT
 */
public interface CustomKillSourceItem {
    DamageSource getKillSource(LivingEntity living);

    static boolean isHolding(Entity entity) {
        return entity instanceof LivingEntity living && living.getMainHandItem().getItem() instanceof CustomKillSourceItem;
    }
}
