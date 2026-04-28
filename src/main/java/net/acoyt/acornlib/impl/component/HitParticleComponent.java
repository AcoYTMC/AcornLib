package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

/**
 * @author AcoYT
 */
public record HitParticleComponent(Identifier particle, int count) {
    public static final HitParticleComponent DEFAULT = new HitParticleComponent(Identifier.ofVanilla("sweep_attack"), 1);

    public static final Codec<HitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Identifier.CODEC.fieldOf("particle").forGetter(HitParticleComponent::particle),
            Codec.INT.fieldOf("count").forGetter(HitParticleComponent::count)
    ).apply(builder, HitParticleComponent::new));

    public static final PacketCodec<ByteBuf, HitParticleComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
