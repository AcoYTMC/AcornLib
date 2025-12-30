package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.acoyt.acornlib.impl.client.event.BlacklistEvent;
import net.acoyt.acornlib.impl.client.event.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.init.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        HeldItemPredicate.registerHeldModelPredicate();
        AcornBlockEntities.clientInit();
        AcornBlocks.clientInit();
        AcornParticles.clientInit();

        AcornClientRegistries.init();
        AcornArmPoses.init();

        ClientTickEvents.END_CLIENT_TICK.register(new SupportersOnlyEvent());
        ClientTickEvents.END_CLIENT_TICK.register(new BlacklistEvent());
    }
}
