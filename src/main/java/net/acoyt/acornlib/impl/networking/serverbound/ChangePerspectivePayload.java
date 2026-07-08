package net.acoyt.acornlib.impl.networking.serverbound;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

/**
 * @author AcoYT
 */
public record ChangePerspectivePayload(String perspective) implements CustomPacketPayload {
    public static final Type<ChangePerspectivePayload> TYPE = new Type<>(AcornLib.id("change_perspective"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ChangePerspectivePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ChangePerspectivePayload::perspective,
            ChangePerspectivePayload::new
    );

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Receiver implements ServerPlayNetworking.PlayPayloadHandler<ChangePerspectivePayload> {
        public void receive(ChangePerspectivePayload payload, ServerPlayNetworking.Context context) {
            ServerPlayer player = context.player();
            AcornData.KEY.get(player).setStoredPerspective(payload.perspective());
        }
    }
}
