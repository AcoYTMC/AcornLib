package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.client.renderer.entity.state.PlayerRenderState;

public interface PlayerEntityModelAccess {
    boolean acornLib$isThinArms();
    //void acornLib$setThinArms(boolean thinArms);

    PlayerRenderState acornLib$getRenderState();
    void acornLib$setRenderState(PlayerRenderState renderState);
}
