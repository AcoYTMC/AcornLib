package net.acoyt.acornlib.impl.mixin.ghast;

import net.acoyt.acornlib.impl.util.interfaces.HappyGhastHolder;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HappyGhastEntityRenderState.class)
public class HappyGhastEntityRenderStateMixin implements HappyGhastHolder {
    @Unique private HappyGhastEntity happyGhast;

    @Override
    public HappyGhastEntity acornLib$getHappyGhast() {
        return this.happyGhast;
    }

    @Override
    public void acornLib$setHappyGhast(HappyGhastEntity happyGhast) {
        this.happyGhast = happyGhast;
    }
}
