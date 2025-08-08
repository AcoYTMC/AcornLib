package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record SweepParticleComponent(int baseColor, int shadowColor) {
    public static final SweepParticleComponent DEFAULT = new SweepParticleComponent(0xFFFFFFFF, 0xFFDEDEDE);

    public static final Codec<SweepParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Codecs.RGB.fieldOf("base").forGetter(SweepParticleComponent::baseColor),
            Codecs.RGB.fieldOf("shadow").forGetter(SweepParticleComponent::shadowColor)
    ).apply(builder, SweepParticleComponent::new));
}
