package net.acoyt.acornlib.api.particles;

//~ if > 1.21.11 'UniquelyIdentifiable' -> 'StringRepresentable' {
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.networking.clientbound.CustomParticlePayload;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Quaternionf;

//? if > 1.21.4 {
/*import net.minecraft.world.entity.EntityReference;
import org.joml.Quaternionfc;
import net.minecraft.world.level.entity.UniquelyIdentifyable;
*///? }

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author AcoYT
 */
@ApiStatus.Experimental
public class ParticleBuilder {
    private final Level level;

    private List<? extends Player> viewers;
    private ParticleOptions particle;
    private Vec3 position;
    private Vec3 velocity = Vec3.ZERO;
    private float size = 1.0F;
    private Quaternionf rotation = new Quaternionf();
    private int color = 0xFFFFFF;

    private ParticleBuilder(Level level) {
        this.level = level;
        this.viewers = level.players();
    }

    public static ParticleBuilder builder(Level level) {
        return new ParticleBuilder(level);
    }

    public ParticleBuilder viewers(List<Player> viewers) {
        this.viewers = viewers;
        return this;
    }

    public ParticleBuilder predicate(Predicate<Player> predicate) {
        this.viewers.removeIf(player -> !predicate.test(player));
        return this;
    }

    public ParticleBuilder type(ParticleOptions particle) {
        this.particle = particle;
        return this;
    }

    public ParticleBuilder position(Vec3 position) {
        this.position = position;
        return this;
    }

    public ParticleBuilder position(double x, double y, double z) {
        this.position = new Vec3(x, y, z);
        return this;
    }

    public ParticleBuilder velocity(Vec3 velocity) {
        this.velocity = velocity;
        return this;
    }

    public ParticleBuilder velocity(double x, double y, double z) {
        this.velocity = new Vec3(x, y, z);
        return this;
    }

    public ParticleBuilder size(float size) {
        this.size = size;
        return this;
    }

    public ParticleBuilder rotation(float x, float y, float z) {
        this.rotation.rotateXYZ(x, y, z);
        return this;
    }

    public ParticleBuilder rotation(Vec3 rotation) {
        this.rotation.rotateXYZ((float) rotation.x, (float) rotation.y, (float) rotation.z);
        return this;
    }

    public ParticleBuilder rotation(Consumer<Quaternionf> consumer) {
        consumer.accept(this.rotation);
        return this;
    }

    public ParticleBuilder rotationFunc(Function<Quaternionf, Quaternionf> function) {
        this.rotation = function.apply(this.rotation);
        return this;
    }

    public ParticleBuilder color(int color) {
        this.color = color;
        return this;
    }

    public ParticleBuilder color(String color) {
        this.color = AcornUtil.convertToHex(color);
        return this;
    }

    public void spawn() {
        if (this.viewers.isEmpty()) return;
        if (this.level.isClientSide()) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player != null && this.viewers.contains(player)) {
                AcornLib.LOGGER.info("Created particle at [{}, {}, {}] with type {}, viewed by {}", this.position.x, this.position.y, this.position.z, BuiltInRegistries.PARTICLE_TYPE.getKey(this.particle.getType()), player.getDisplayName().getString());
                this.level.addParticle(
                        this.toParticleData(),
                        this.position.x, this.position.y, this.position.z,
                        this.velocity.x, this.velocity.y, this.velocity.z
                );
            }
        } else {
            //? if > 1.21.4 {
            /*for (Player player : this.viewers) {
                if (player instanceof ServerPlayer serverPlayer) {
                    ServerPlayNetworking.send(serverPlayer, new CustomParticlePayload(this.toParticleData()));
                }
            }
            *///? } else {
            for (Player player : this.level.players()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    ServerPlayNetworking.send(serverPlayer, new CustomParticlePayload(this.toParticleData()));
                }
            }
            //? }
        }
    }

    //? if > 1.21.4 {
    /*public Data toData() {
        return new Data(
                this.viewers.stream().map(p -> EntityReference.of(p.getUUID())).toList(),
                this.particle,
                this.position,
                this.velocity,
                this.size,
                this.rotation,
                this.color
        );
    }
    *///? }

    public SpecialParticleData toParticleData() {
        return new SpecialParticleData(
                this.particle,
                this.position,
                this.velocity,
                this.size,
                this.rotation,
                this.color
        );
    }

    //~ if > 1.21.10 'Quaternionf' -> 'Quaternionfc' {
    //? if > 1.21.4 {
    /*public record Data(List<EntityReference<UniquelyIdentifyable>> viewers, ParticleOptions particle, Vec3 position,
                       Vec3 velocity, float size, Quaternionf rotation, int color) {
        public static final Codec<Data> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                EntityReference.codec().listOf().fieldOf("viewers").forGetter(Data::viewers),
                ParticleTypes.CODEC.fieldOf("particle").forGetter(Data::particle),
                Vec3.CODEC.fieldOf("position").forGetter(Data::position),
                Vec3.CODEC.fieldOf("velocity").forGetter(Data::velocity),
                Codec.FLOAT.fieldOf("size").forGetter(Data::size),
                ExtraCodecs.QUATERNIONF.fieldOf("rotation").forGetter(Data::rotation),
                ExtraCodecs.ARGB_COLOR_CODEC.optionalFieldOf("color", 0xFFFFFF).forGetter(Data::color)
        ).apply(instance, Data::new));

        public static final StreamCodec<ByteBuf, Data> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }
    *///? } else {
    public record Data(ParticleOptions particle, Vec3 position,
                       Vec3 velocity, float size, Quaternionf rotation, int color) {
        public static final Codec<Data> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                ParticleTypes.CODEC.fieldOf("particle").forGetter(Data::particle),
                Vec3.CODEC.fieldOf("position").forGetter(Data::position),
                Vec3.CODEC.fieldOf("velocity").forGetter(Data::velocity),
                Codec.FLOAT.fieldOf("size").forGetter(Data::size),
                ExtraCodecs.QUATERNIONF.fieldOf("rotation").forGetter(Data::rotation),
                ExtraCodecs.ARGB_COLOR_CODEC.optionalFieldOf("color", 0xFFFFFF).forGetter(Data::color)
        ).apply(instance, Data::new));

        public static final StreamCodec<ByteBuf, Data> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
    }
    //? }
    //~ }
}
//~ }