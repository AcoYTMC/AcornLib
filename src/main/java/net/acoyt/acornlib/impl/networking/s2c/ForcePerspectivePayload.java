package net.acoyt.acornlib.impl.networking.s2c;

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

public record ForcePerspectivePayload(String perspective) implements CustomPayload {
    public static final CustomPayload.Id<ForcePerspectivePayload> ID = new Id<>(AcornLib.id("force_perspective"));

    public static final PacketCodec<RegistryByteBuf, ForcePerspectivePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            ForcePerspectivePayload::perspective,
            ForcePerspectivePayload::new
    );

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<ForcePerspectivePayload> {
        public void receive(ForcePerspectivePayload payload, ClientPlayNetworking.Context context) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.player == null) return;
            Perspective perspective = null;
            for (Perspective per : Perspective.values()) {
                if (per.name().equalsIgnoreCase(payload.perspective())) {
                    perspective = per;
                }
            }

            if (perspective != null) {
                client.options.setPerspective(perspective);
                KEY.get(client.player).setStoredPerspective(payload.perspective());
            }
        }
    }
}
