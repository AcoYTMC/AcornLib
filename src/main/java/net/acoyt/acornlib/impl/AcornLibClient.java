package net.acoyt.acornlib.impl;

import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.acoyt.acornlib.impl.event.client.BlacklistEvent;
import net.acoyt.acornlib.impl.event.client.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.networking.AcornNetworking;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    public static final List<Identifier> models = new ArrayList<>();

    public void onInitializeClient() {
        HeldItemPredicate.registerHeldModelPredicate();

        /* Initialization */
        AcornBlockEntities.clientInit();
        AcornBlocks.clientInit();
        AcornParticles.clientInit();

        /* Networking */
        AcornNetworking.registerS2CPackets();

        /* Events */
        ClientTickEvents.END_CLIENT_TICK.register(new SupportersOnlyEvent());
        ClientTickEvents.END_CLIENT_TICK.register(new BlacklistEvent());
    }
}
