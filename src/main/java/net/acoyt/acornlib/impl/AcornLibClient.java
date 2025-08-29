package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.init.AcornBlockEntities;
import net.acoyt.acornlib.impl.init.AcornBlocks;
import net.acoyt.acornlib.impl.init.AcornParticles;
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
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && ALib.getSupporterRequired() && !AcornLib.isSupporter(client.player)) {
                throw new RuntimeException("This mod is for supporters only. Consider supporting, it makes everything I do possible :3");
            }
        });
    }
}
