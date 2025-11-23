package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.world.entity.player.Player;

public interface PlayerEntityRenderStateAccess {
    void acornLib$setPlayerEntity(Player player);
    Player acornLib$getPlayerEntity();
}
