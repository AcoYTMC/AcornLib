package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.util.PortingUtils;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record SweepParticleComponent(int baseColor, int shadowColor) {
    public static final SweepParticleComponent DEFAULT = new SweepParticleComponent(0xFFFFFFFF, 0xFFDEDEDE);

    public static final Codec<SweepParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            PortingUtils.RGB.fieldOf("base").forGetter(SweepParticleComponent::baseColor),
            PortingUtils.RGB.fieldOf("shadow").forGetter(SweepParticleComponent::shadowColor)
    ).apply(builder, SweepParticleComponent::new));

    public static final PacketCodec<RegistryByteBuf, SweepParticleComponent> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER,
            SweepParticleComponent::baseColor,
            PacketCodecs.INTEGER,
            SweepParticleComponent::shadowColor,
            SweepParticleComponent::new
    );
}
