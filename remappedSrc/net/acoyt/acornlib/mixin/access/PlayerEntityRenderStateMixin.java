package net.acoyt.acornlib.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/// Adds the player as a field in {@link PlayerEntityRenderState}
@Mixin(PlayerRenderState.class)
public abstract class PlayerEntityRenderStateMixin implements PlayerEntityRenderStateAccess {
    @Unique private Player player;

    @Override
    public void acornLib$setPlayerEntity(Player player) {
        this.player = player;
    }

    @Override
    public Player acornLib$getPlayerEntity() {
        return this.player;
    }
}
