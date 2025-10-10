package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.client.render.entity.state.PlayerEntityRenderState;

public interface PlayerEntityModelAccess {
    boolean acornLib$isThinArms();

    PlayerEntityRenderState acornLib$getRenderState();
    void acornLib$setRenderState(PlayerEntityRenderState renderState);
}
