package net.acoyt.acornlib.impl.client.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.util.PortingUtils;
import net.acoyt.acornlib.impl.init.AcornParticles;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;

public record SweepParticleEffect(int baseColor, int shadowColor) implements ParticleEffect {
    public static final MapCodec<SweepParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            PortingUtils.RGB.fieldOf("base").forGetter(SweepParticleEffect::baseColor),
            PortingUtils.RGB.fieldOf("shadow").forGetter(SweepParticleEffect::shadowColor)
    ).apply(instance, SweepParticleEffect::new));

    public static final PacketCodec<RegistryByteBuf, SweepParticleEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,
            SweepParticleEffect::baseColor,
            PacketCodecs.INTEGER,
            SweepParticleEffect::shadowColor,
            SweepParticleEffect::new
    );

    @Override
    public ParticleType<?> getType() {
        return AcornParticles.SWEEP_PARTICLE;
    }
}
