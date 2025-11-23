package net.acoyt.acornlib.impl.client.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.impl.init.AcornParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;

public record SweepParticleEffect(int baseColor, int shadowColor) implements ParticleOptions {
    public static final MapCodec<SweepParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("base").forGetter(SweepParticleEffect::baseColor),
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("shadow").forGetter(SweepParticleEffect::shadowColor)
    ).apply(instance, SweepParticleEffect::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SweepParticleEffect> PACKET_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SweepParticleEffect::baseColor,
            ByteBufCodecs.INT,
            SweepParticleEffect::shadowColor,
            SweepParticleEffect::new
    );

    @Override
    public ParticleType<?> getType() {
        return AcornParticles.SWEEP_PARTICLE;
    }
}
