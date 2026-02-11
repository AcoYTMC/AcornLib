package net.acoyt.acornlib.impl.event;

import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.impl.networking.SyncChangingRulePayload;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

public class SendUpdateRulePayloadEvent implements ServerPlayConnectionEvents.Join {
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        if (handler.player == null) return;
        ServerPlayNetworking.send(handler.player, new SyncChangingRulePayload(server.getGameRules().getBoolean(AcornGameRules.ALLOW_PERSPECTIVE_CHANGING)));
    }
}
