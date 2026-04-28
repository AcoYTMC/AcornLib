package net.acoyt.acornlib.api.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.jetbrains.annotations.Nullable;

/**
 * @author AcoYT
 */
public interface CustomKillSourceItem {
    DamageSource getKillSource(LivingEntity living, @Nullable Entity attacker, float amount);
}
