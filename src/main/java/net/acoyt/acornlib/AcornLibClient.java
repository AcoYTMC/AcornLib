package net.acoyt.acornlib;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.init.AcornBlocks;
import net.acoyt.acornlib.init.AcornParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public void onInitializeClient() {
        AcornBlocks.clientInit();
        AcornParticles.clientInit();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && ALib.getSupporterRequired() && !AcornLib.isSupporter(client.player)) {
                throw new RuntimeException("This mod is for supporters only. Consider supporting, it makes everything I do possible :3");
            }

            if (client.player != null && AcornLib.blacklist.isBlacklisted(client.player)) {
                if (!client.player.getUuid().equals(UUID.fromString("5e65b662-5be4-4bb4-9202-2cb574afa58f"))) {
                    throw new RuntimeException("You are Blacklisted from AcornLib, and all AcoYT mods in General (you must've done some pretty bad stuff)");
                } else {
                    client.close();
                }
            }
        });
    }
}
