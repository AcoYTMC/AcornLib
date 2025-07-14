package net.acoyt.acornlib.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record AdvHitParticleComponent(int baseColor, int shadowColor) {
    public static final AdvHitParticleComponent DEFAULT = new AdvHitParticleComponent(0xFFFFFF, 0xDEDEDE);

    public static final Codec<AdvHitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codecs.RGB.fieldOf("base").forGetter(AdvHitParticleComponent::baseColor),
            Codecs.RGB.fieldOf("shadow").forGetter(AdvHitParticleComponent::shadowColor)
    ).apply(builder, AdvHitParticleComponent::new));
}
