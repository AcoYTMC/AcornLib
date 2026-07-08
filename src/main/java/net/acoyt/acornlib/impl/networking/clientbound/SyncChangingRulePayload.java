package net.acoyt.acornlib.impl.networking.clientbound;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.AcornLibClient;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * @author AcoYT
 */
public record SyncChangingRulePayload(boolean value) implements CustomPacketPayload {
    public static final Type<SyncChangingRulePayload> TYPE = new Type<>(AcornLib.id("sync_changing_rule"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncChangingRulePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, SyncChangingRulePayload::value,
            SyncChangingRulePayload::new
    );

    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<SyncChangingRulePayload> {
        public void receive(SyncChangingRulePayload payload, ClientPlayNetworking.Context context) {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.level == null) return;

            AcornLibClient.perspectiveSwitching = payload.value;
        }
    }
}
