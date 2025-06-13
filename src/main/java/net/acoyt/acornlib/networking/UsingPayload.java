package net.acoyt.acornlib.networking;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.util.interfaces.AcornPlayerEntity;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import org.jetbrains.annotations.NotNull;

public record UsingPayload(boolean using) implements CustomPayload {
    public static final Id<UsingPayload> ID = new CustomPayload.Id<>(AcornLib.id("using"));
    public static final PacketCodec<PacketByteBuf, UsingPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOLEAN, UsingPayload::using, UsingPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<UsingPayload> {
        @Override
        public void receive(@NotNull UsingPayload payload, ServerPlayNetworking.@NotNull Context context) {
            if (context.player() instanceof AcornPlayerEntity acornPlayer) {
                acornPlayer.acornlib$setHoldingUse(payload.using());
            }
        }
    }
}
