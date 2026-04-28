package net.acoyt.acornlib.api.template;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;

/**
 * @author AcoYT
 */
public class StatusEffectBase extends StatusEffect {
    public StatusEffectBase(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public StatusEffectBase(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }
}
