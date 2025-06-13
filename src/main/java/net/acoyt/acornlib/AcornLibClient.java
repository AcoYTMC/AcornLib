package net.acoyt.acornlib;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.init.AcornEntities;
import net.acoyt.acornlib.init.AcornParticles;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        AcornBlocks.clientInit();
        AcornEntities.clientInit();
        AcornParticles.clientInit();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && ALib.getSupporterRequired() && !(AcornLibUtils.supporterUtils.isPlayerSupporter(client.player) || AcornLibUtils.supporterUtils.isPlayerFriend(client.player))) {
                throw new RuntimeException("This mod is for supporters only. Consider supporting, it makes everything I do possible :3");
            }
        });
    }
}
