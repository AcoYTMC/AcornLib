package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.impl.index.AcornCriterions;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDeathCriterionEvent implements ServerLivingEntityEvents.AfterDeath {
    public void afterDeath(LivingEntity living, DamageSource source) {
        if (living instanceof ServerPlayerEntity player) {
            AcornCriterions.PLAYER_DEATH.trigger(player);
        }
    }
}
