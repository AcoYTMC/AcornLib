package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.ExtraCodecs;

public record SweepParticleComponent(int baseColor, int shadowColor) {
    public static final SweepParticleComponent DEFAULT = new SweepParticleComponent(0xFFFFFFFF, 0xFFDEDEDE);

    public static final Codec<SweepParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("base").forGetter(SweepParticleComponent::baseColor),
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("shadow").forGetter(SweepParticleComponent::shadowColor)
    ).apply(builder, SweepParticleComponent::new));
}
