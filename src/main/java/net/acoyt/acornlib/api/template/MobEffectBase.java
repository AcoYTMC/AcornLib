package net.acoyt.acornlib.api.template;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * @author AcoYT
 */
public class MobEffectBase extends MobEffect {
    public MobEffectBase(MobEffectCategory category, int color) {
        super(category, color);
    }

    public MobEffectBase(MobEffectCategory category, int color, ParticleOptions particleEffect) {
        super(category, color, particleEffect);
    }
}
