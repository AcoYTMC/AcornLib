package net.acoyt.acornlib.impl.client.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

//? if > 1.21.5 {
import net.minecraft.util.ExtraCodecs;
//? } else {
/*import net.acoyt.acornlib.api.util.PortingUtils;
*///? }

/**
 * @author AcoYT
 */
public record SweepParticleEffect(int baseColor, int shadowColor) implements ParticleOptions {
    public static final MapCodec<SweepParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            //? if > 1.21.5 {
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("base").forGetter(SweepParticleEffect::baseColor),
            ExtraCodecs.RGB_COLOR_CODEC.fieldOf("shadow").forGetter(SweepParticleEffect::shadowColor)
            //? } else {
            /*PortingUtils.RGB.fieldOf("base").forGetter(SweepParticleEffect::baseColor),
            PortingUtils.RGB.fieldOf("shadow").forGetter(SweepParticleEffect::shadowColor)
            *///? }
    ).apply(instance, SweepParticleEffect::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SweepParticleEffect> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SweepParticleEffect::baseColor,
            ByteBufCodecs.INT,
            SweepParticleEffect::shadowColor,
            SweepParticleEffect::new
    );

    public ParticleType<?> getType() {
        return AcornParticles.SWEEP_PARTICLE;
    }
}
