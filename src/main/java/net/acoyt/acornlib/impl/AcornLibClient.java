package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.acoyt.acornlib.impl.client.event.BlacklistEvent;
import net.acoyt.acornlib.impl.client.event.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.acoyt.acornlib.impl.index.client.AcornArmPoses;
import net.acoyt.acornlib.impl.index.client.AcornClientRegistries;
import net.acoyt.acornlib.impl.networking.ForcePerspectiveReceiver;
import net.acoyt.acornlib.impl.networking.SyncChangingRuleReceiver;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

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
        ForcePerspectiveReceiver.registerClientPacket();
        SyncChangingRuleReceiver.registerClientPacket();
    }
}
