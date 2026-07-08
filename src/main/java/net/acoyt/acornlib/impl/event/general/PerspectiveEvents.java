package net.acoyt.acornlib.impl.event.general;

import net.acoyt.acornlib.impl.AcornLibClient;
import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.impl.networking.clientbound.SyncChangingRulePayload;
import net.acoyt.acornlib.impl.networking.serverbound.ChangePerspectivePayload;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.server.level.ServerPlayer;

/**
 * @author AcoYT
 */
public class PerspectiveEvents {
    public static class ServerJoin implements ServerPlayerEvents.Join {
        public void onJoin(ServerPlayer player) {
            //~ if > 1.21.10 'getGameRules().getBoolean' -> 'getGameRules().get'
            ServerPlayNetworking.send(player, new SyncChangingRulePayload(player.level().getGameRules().getBoolean(AcornGameRules.PERSPECTIVE_CHANGING)));
        }
    }

    @Environment(EnvType.CLIENT)
    public static class ClientJoin implements ClientPlayConnectionEvents.Join {
        public void onPlayReady(ClientPacketListener handler, PacketSender packetSender, Minecraft client) {
            if (client.player != null) {
                ClientPlayNetworking.send(new ChangePerspectivePayload(client.options.getCameraType().name()));
            }
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Disconnect implements ClientPlayConnectionEvents.Disconnect {
        public void onPlayDisconnect(ClientPacketListener handler, Minecraft client) {
            AcornLibClient.perspectiveSwitching = true;
        }
    }
}
