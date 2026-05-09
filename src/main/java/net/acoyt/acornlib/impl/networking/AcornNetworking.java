package net.acoyt.acornlib.impl.networking;

import net.acoyt.acornlib.impl.networking.s2c.ForcePerspectivePayload;
import net.acoyt.acornlib.impl.networking.s2c.SyncChangingRulePayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public interface AcornNetworking {
    static void registerTypes() {
        PayloadTypeRegistry.playS2C().register(ForcePerspectivePayload.ID, ForcePerspectivePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncChangingRulePayload.ID, SyncChangingRulePayload.CODEC);
    }

    static void registerC2SPackets() {
        //
    }

    @Environment(EnvType.CLIENT)
    static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ForcePerspectivePayload.ID, new ForcePerspectivePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(SyncChangingRulePayload.ID, new SyncChangingRulePayload.Receiver());
    }
}
