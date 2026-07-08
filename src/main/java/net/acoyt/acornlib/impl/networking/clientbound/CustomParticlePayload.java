package net.acoyt.acornlib.impl.networking.clientbound;

import io.netty.buffer.ByteBuf;
import net.acoyt.acornlib.api.particles.SpecialParticleData;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

/**
 * @author AcoYT
 */
public record CustomParticlePayload(SpecialParticleData particleData) implements CustomPacketPayload {
    public static final Type<CustomParticlePayload> TYPE = new Type<>(AcornLib.id("custom_particle"));

    public static final StreamCodec<ByteBuf, CustomParticlePayload> CODEC = StreamCodec.composite(
            SpecialParticleData.STREAM_CODEC, CustomParticlePayload::particleData,
            CustomParticlePayload::new
    );

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<CustomParticlePayload> {
        public void receive(CustomParticlePayload payload, ClientPlayNetworking.Context context) {
            Minecraft minecraft = context.client();
            if (minecraft.level == null) return;
            SpecialParticleData data = payload.particleData;
            Vec3 pos = data.position();
            Vec3 vel = data.velocity();

            minecraft.level.addParticle(
                    data,
                    pos.x, pos.y, pos.z,
                    vel.x, vel.y, vel.z
            );
        }
    }
}
