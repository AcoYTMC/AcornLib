package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * @author AcoYT
 */
public record HitParticleComponent(ParticleOptions particle, int count) {
    public static final HitParticleComponent DEFAULT = new HitParticleComponent(ParticleTypes.SWEEP_ATTACK, 1);

    public static final Codec<HitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            ParticleTypes.CODEC.fieldOf("particle").forGetter(HitParticleComponent::particle),
            Codec.INT.fieldOf("count").forGetter(HitParticleComponent::count)
    ).apply(builder, HitParticleComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HitParticleComponent> PACKET_CODEC = StreamCodec.composite(
            ParticleTypes.STREAM_CODEC, HitParticleComponent::particle,
            ByteBufCodecs.INT, HitParticleComponent::count,
            HitParticleComponent::new
    );
}
