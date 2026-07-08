package net.acoyt.acornlib.impl.networking.clientbound;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.networking.serverbound.ChangePerspectivePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * @author AcoYT
 */
public record ForcePerspectivePayload(String perspective) implements CustomPacketPayload {
    public static final Type<ForcePerspectivePayload> TYPE = new Type<>(AcornLib.id("force_perspective"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ForcePerspectivePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ForcePerspectivePayload::perspective,
            ForcePerspectivePayload::new
    );

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<ForcePerspectivePayload> {
        public void receive(ForcePerspectivePayload payload, ClientPlayNetworking.Context context) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player == null) return;
            CameraType perspective = null;
            for (CameraType per : CameraType.values()) {
                if (per.name().equalsIgnoreCase(payload.perspective())) {
                    perspective = per;
                }
            }

            if (perspective != null) {
                minecraft.options.setCameraType(perspective);
                ClientPlayNetworking.send(new ChangePerspectivePayload(perspective.name()));
            }
        }
    }
}
