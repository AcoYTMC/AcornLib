package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.acoyt.acornlib.impl.client.event.BlacklistEvent;
import net.acoyt.acornlib.impl.client.event.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.acoyt.acornlib.impl.index.client.AcornArmPoses;
import net.acoyt.acornlib.impl.index.client.AcornClientRegistries;
import net.acoyt.acornlib.impl.networking.s2c.ForcePerspectivePayload;
import net.acoyt.acornlib.impl.networking.s2c.SyncChangingRulePayload;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        HeldItemPredicate.registerHeldModelPredicate();

        // Initialization
        AcornArmPoses.init();
        AcornBlockEntities.clientInit();
        AcornBlocks.clientInit();
        AcornClientRegistries.init();
        AcornParticles.clientInit();

        // Events
        ClientTickEvents.END_CLIENT_TICK.register(new SupportersOnlyEvent());
        ClientTickEvents.END_CLIENT_TICK.register(new BlacklistEvent());

        // Networking
        ClientPlayNetworking.registerGlobalReceiver(ForcePerspectivePayload.ID, new ForcePerspectivePayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(SyncChangingRulePayload.ID, new SyncChangingRulePayload.Receiver());
    }
}
