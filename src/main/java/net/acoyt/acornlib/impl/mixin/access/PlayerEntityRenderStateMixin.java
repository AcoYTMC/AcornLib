package net.acoyt.acornlib.impl.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/// Adds the player as a field in {@link PlayerEntityRenderState}
@Mixin(PlayerEntityRenderState.class)
public abstract class PlayerEntityRenderStateMixin implements PlayerEntityRenderStateAccess {
    @Unique private PlayerEntity player;

    @Override
    public void acornLib$setPlayerEntity(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public PlayerEntity acornLib$getPlayerEntity() {
        return this.player;
    }
}
