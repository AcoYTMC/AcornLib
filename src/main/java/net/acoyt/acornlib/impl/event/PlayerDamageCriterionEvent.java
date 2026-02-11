package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.impl.index.AcornCriterions;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDamageCriterionEvent implements ServerLivingEntityEvents.AfterDamage {
    public void afterDamage(LivingEntity living, DamageSource source, float baseDamageTaken, float damageTaken, boolean blocked) {
        if (living instanceof ServerPlayerEntity player) {
            AcornCriterions.PLAYER_DAMAGE.trigger(player);
        }
    }
}
