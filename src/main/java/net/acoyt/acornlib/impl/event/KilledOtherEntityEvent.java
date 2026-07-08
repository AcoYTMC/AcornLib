package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.api.item.KillEffectItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

//? if > 1.21.8 {
import net.minecraft.world.damagesource.DamageSource;
//? }

/**
 * @author AcoYT
 */
public class KilledOtherEntityEvent implements ServerEntityCombatEvents.AfterKilledOtherEntity {
    //? if > 1.21.8 {
    public void afterKilledOtherEntity(ServerLevel level, Entity entity, LivingEntity victim, DamageSource source) {
        if (entity instanceof LivingEntity living) {
            if (living.getMainHandItem().getItem() instanceof KillEffectItem killEffectItem) {
                killEffectItem.killEntity(level, living.getMainHandItem(), living, victim);
            }
        }
    }
    //? } else {
    /*public void afterKilledOtherEntity(ServerLevel level, Entity entity, LivingEntity victim) {
        if (entity instanceof LivingEntity living) {
            if (living.getMainHandItem().getItem() instanceof KillEffectItem killEffectItem) {
                killEffectItem.killEntity(level, living.getMainHandItem(), living, victim);
            }
        }
    }
    *///? }
}
