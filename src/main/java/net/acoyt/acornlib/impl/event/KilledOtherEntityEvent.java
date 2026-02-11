package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.api.item.KillEffectItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;

public class KilledOtherEntityEvent implements ServerEntityCombatEvents.AfterKilledOtherEntity {
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity victim) {
        if (entity instanceof LivingEntity living) {
            if (living.getMainHandStack().getItem() instanceof KillEffectItem killEffectItem) {
                killEffectItem.killEntity(world, living.getMainHandStack(), living, victim);
            }
        }
    }
}
