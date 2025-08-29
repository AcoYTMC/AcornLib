package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.entity.player.PlayerEntity;

public interface PlayerEntityRenderStateAccess {
    void acornLib$setPlayerEntity(PlayerEntity player);
    PlayerEntity acornLib$getPlayerEntity();
}
