package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.impl.index.AcornCriteria;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

/**
 * @author AcoYT
 */
public class PlayerDeathCriterionEvent implements ServerLivingEntityEvents.AfterDeath {
    public void afterDeath(LivingEntity living, DamageSource source) {
        if (living instanceof ServerPlayer player) {
            AcornCriteria.PLAYER_DEATH.trigger(player);
        }
    }
}
