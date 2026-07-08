package net.acoyt.acornlib.impl.networking;

import net.acoyt.acornlib.impl.networking.clientbound.CustomParticlePayload;
import net.acoyt.acornlib.impl.networking.clientbound.ForcePerspectivePayload;
import net.acoyt.acornlib.impl.networking.clientbound.SyncChangingRulePayload;
import net.acoyt.acornlib.impl.networking.serverbound.ChangePerspectivePayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

/**
 * @author AcoYT
 */
public interface AcornNetworking {
    static void registerTypes() {
        //? if > 1.21.11 {
        /*PayloadTypeRegistry.clientboundPlay().register(ForcePerspectivePayload.TYPE, ForcePerspectivePayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(SyncChangingRulePayload.TYPE, SyncChangingRulePayload.CODEC);
        PayloadTypeRegistry.clientboundPlay().register(CustomParticlePayload.TYPE, CustomParticlePayload.CODEC);

        PayloadTypeRegistry.serverboundPlay().register(ChangePerspectivePayload.TYPE, ChangePerspectivePayload.CODEC);
        *///? } else {
        PayloadTypeRegistry.playS2C().register(ForcePerspectivePayload.TYPE, ForcePerspectivePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SyncChangingRulePayload.TYPE, SyncChangingRulePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CustomParticlePayload.TYPE, CustomParticlePayload.CODEC);

        PayloadTypeRegistry.playC2S().register(ChangePerspectivePayload.TYPE, ChangePerspectivePayload.CODEC);
        //? }
    }

    static void registerServerboundPackets() {
        ServerPlayNetworking.registerGlobalReceiver(ChangePerspectivePayload.TYPE, new ChangePerspectivePayload.Receiver());
    }

    @Environment(EnvType.CLIENT)
    static void registerClientboundPackets() {
        ClientPlayNetworking.registerGlobalReceiver(ForcePerspectivePayload.TYPE, new ForcePerspectivePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(SyncChangingRulePayload.TYPE, new SyncChangingRulePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(CustomParticlePayload.TYPE, new CustomParticlePayload.Receiver());
    }
}
