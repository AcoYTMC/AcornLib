package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.impl.client.event.BlacklistEvent;
import net.acoyt.acornlib.impl.client.event.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        AcornBlockEntities.clientInit();
        AcornBlocks.clientInit();
        AcornParticles.clientInit();
        ClientTickEvents.END_CLIENT_TICK.register(new SupportersOnlyEvent());
        ClientTickEvents.END_CLIENT_TICK.register(new BlacklistEvent());
    }
}
