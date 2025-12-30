package net.acoyt.acornlib.api.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface CustomKillSourceItem {
    DamageSource getKillSource(LivingEntity living);
}
