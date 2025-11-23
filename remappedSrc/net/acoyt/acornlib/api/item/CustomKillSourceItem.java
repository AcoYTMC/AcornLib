package net.acoyt.acornlib.api.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public interface CustomKillSourceItem {
    DamageSource getKillSource(LivingEntity living);
}
