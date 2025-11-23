package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record HitParticleComponent(ResourceLocation particle, int count) {
    public static final HitParticleComponent DEFAULT = new HitParticleComponent(ResourceLocation.withDefaultNamespace("sweep_attack"), 1);

    public static final Codec<HitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ResourceLocation.CODEC.fieldOf("particle").forGetter(HitParticleComponent::particle),
            Codec.INT.optionalFieldOf("count", 1).forGetter(HitParticleComponent::count)
    ).apply(builder, HitParticleComponent::new));
}
