package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

/**
 * @author AcoYT
 */
public record HitParticleComponent(Identifier particle, int count) {
    public static final HitParticleComponent DEFAULT = new HitParticleComponent(Identifier.withDefaultNamespace("sweep_attack"), 1);

    public static final Codec<HitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Identifier.CODEC.fieldOf("particle").forGetter(HitParticleComponent::particle),
            Codec.INT.fieldOf("count").forGetter(HitParticleComponent::count)
    ).apply(builder, HitParticleComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HitParticleComponent> PACKET_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, HitParticleComponent::particle,
            ByteBufCodecs.INT, HitParticleComponent::count,
            HitParticleComponent::new
    );
}
