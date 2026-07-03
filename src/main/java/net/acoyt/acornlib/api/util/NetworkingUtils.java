package net.acoyt.acornlib.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

/**
 * @author AcoYT
 */
@SuppressWarnings("unused")
public class NetworkingUtils {
    public static void sendForAllPlayers(MinecraftServer server, CustomPacketPayload payload) {
        sendForAllPlayers(server, payload, player -> true);
    }

    public static void sendForAllPlayers(Level level, CustomPacketPayload payload) {
        sendForAllPlayers(level, payload, player -> true);
    }

    public static void sendForAllPlayers(MinecraftServer server, CustomPacketPayload payload, Predicate<ServerPlayer> predicate) {
        if (server != null) {
            server.getPlayerList().getPlayers().forEach(player -> {
                if (predicate.test(player)) ServerPlayNetworking.send(player, payload);
            });
        }
    }

    public static void sendForAllPlayers(Level level, CustomPacketPayload payload, Predicate<ServerPlayer> predicate) {
        if (level != null && level.getServer() != null) {
            sendForAllPlayers(level.getServer(), payload, predicate);
        }
    }
}
