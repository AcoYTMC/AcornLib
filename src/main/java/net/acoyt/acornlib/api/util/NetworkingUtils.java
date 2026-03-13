package net.acoyt.acornlib.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class NetworkingUtils {
    public static void sendForAllPlayers(MinecraftServer server, CustomPayload payload) {
        if (server != null) {
            server.getPlayerManager().getPlayerList().forEach(player -> ServerPlayNetworking.send(player, payload));
        }
    }

    public static void sendForAllPlayers(World world, CustomPayload payload) {
        if (world != null && world.getServer() != null) {
            sendForAllPlayers(world.getServer(), payload);
        }
    }
}
