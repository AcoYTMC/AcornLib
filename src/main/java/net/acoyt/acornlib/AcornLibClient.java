package net.acoyt.acornlib;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.init.AcornParticles;
import net.acoyt.acornlib.util.supporter.SupporterUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        AcornBlocks.clientInit();
        AcornParticles.clientInit();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && ALib.getSupporterRequired() && !(SupporterUtils.isPlayerSupporter(client.player) || SupporterUtils.isPlayerFriend(client.player))) {
                throw new RuntimeException("This mod is for supporters only. Consider supporting, it makes everything I do possible :3");
            }
        });
    }
}
