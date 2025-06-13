package net.acoyt.acornlib.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.encoding.StringEncoding;
import net.minecraft.util.Identifier;

public record HitParticleComponent(Identifier particle) {
    public static final HitParticleComponent DEFAULT = new HitParticleComponent(Identifier.ofVanilla("sweep_attack"));

    public static final Codec<HitParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Identifier.CODEC.fieldOf("particle").forGetter(HitParticleComponent::particle)
    ).apply(builder, HitParticleComponent::new));

    public static final PacketCodec<ByteBuf, HitParticleComponent> PACKET_CODEC = new PacketCodec<>() {
        public HitParticleComponent decode(ByteBuf buf) {
            Identifier particle = Identifier.of(StringEncoding.decode(buf, 256), StringEncoding.decode(buf, 256));
            return new HitParticleComponent(particle);
        }

        public void encode(ByteBuf buf, HitParticleComponent value) {
            StringEncoding.encode(buf, value.particle().getNamespace(), 256);
            StringEncoding.encode(buf, value.particle().getPath(), 256);
        }
    };
}
