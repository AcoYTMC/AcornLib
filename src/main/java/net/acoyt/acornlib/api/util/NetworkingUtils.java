package net.acoyt.acornlib.api.util;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.world.World;

public class NetworkingUtils {
    public static void sendForAllPlayers(World world, CustomPayload payload) {
        if (world != null && world.getServer() != null) {
            world.getServer().getPlayerManager().getPlayerList().forEach(player -> ServerPlayNetworking.send(player, payload));
        }
    }
}
