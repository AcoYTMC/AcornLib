package net.acoyt.acornlib.impl.networking.s2c;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.mixin.access.WorldAccessor;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SyncChangingRulePayload(boolean value) implements CustomPayload {
    public static final Id<SyncChangingRulePayload> ID = new Id<>(AcornLib.id("sync_changing_rule"));

    public static final PacketCodec<RegistryByteBuf, SyncChangingRulePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL,
            SyncChangingRulePayload::value,
            SyncChangingRulePayload::new
    );

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<SyncChangingRulePayload> {
        public void receive(SyncChangingRulePayload payload, ClientPlayNetworking.Context context) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.world == null) return;

            ((WorldAccessor)client.world).acornlib$getProperties().getGameRules().get(AcornGameRules.ALLOW_PERSPECTIVE_CHANGING).set(payload.value(), null);
        }
    }
}
