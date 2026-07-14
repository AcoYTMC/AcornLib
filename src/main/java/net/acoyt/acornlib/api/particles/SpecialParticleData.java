package net.acoyt.acornlib.api.particles;

//~ if > 1.21.10 'Quaternionf' -> 'Quaternionfc' {
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Quaternionf;

/**
 * @author AcoYT
 */
@ApiStatus.Experimental
public record SpecialParticleData(ParticleOptions particle, Vec3 position, Vec3 velocity, float size, Quaternionf rotation, int color) implements ParticleOptions {
    public static final MapCodec<SpecialParticleData> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ParticleTypes.CODEC.fieldOf("particle").forGetter(SpecialParticleData::particle),
            Vec3.CODEC.fieldOf("position").forGetter(SpecialParticleData::position),
            Vec3.CODEC.fieldOf("velocity").forGetter(SpecialParticleData::velocity),
            Codec.FLOAT.fieldOf("size").forGetter(SpecialParticleData::size),
            ExtraCodecs.QUATERNIONF.fieldOf("rotation").forGetter(SpecialParticleData::rotation),
            ExtraCodecs.ARGB_COLOR_CODEC.optionalFieldOf("color", 0xFFFFFF).forGetter(SpecialParticleData::color)
    ).apply(instance, SpecialParticleData::new));

    public static final StreamCodec<ByteBuf, SpecialParticleData> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC.codec());

    public ParticleType<?> getType() {
        return particle.getType();
    }
}
//~ }