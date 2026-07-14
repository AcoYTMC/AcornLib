package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

//? if > 1.21.5 {
/*import net.minecraft.util.ExtraCodecs;
 *///? } else {
import net.acoyt.acornlib.api.util.PortingUtils;
//? }

/**
 * @author AcoYT
 */
public record SweepParticleComponent(int baseColor, int shadowColor) {
    public static final SweepParticleComponent DEFAULT = new SweepParticleComponent(0xFFFFFFFF, 0xFFDEDEDE);

    public static final Codec<SweepParticleComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            //? if > 1.21.5 {
            /*ExtraCodecs.RGB_COLOR_CODEC.fieldOf("base").forGetter(SweepParticleComponent::baseColor),
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("shadow").forGetter(SweepParticleComponent::shadowColor)
            *///? } else {
            PortingUtils.RGB.fieldOf("base").forGetter(SweepParticleComponent::baseColor),
            PortingUtils.RGB.fieldOf("shadow").forGetter(SweepParticleComponent::shadowColor)
            //? }
    ).apply(builder, SweepParticleComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SweepParticleComponent> PACKET_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SweepParticleComponent::baseColor,
            ByteBufCodecs.INT, SweepParticleComponent::shadowColor,
            SweepParticleComponent::new
    );
}
