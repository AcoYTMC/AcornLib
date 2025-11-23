package net.acoyt.acornlib.mixin.ghast;

import net.acoyt.acornlib.impl.util.interfaces.HappyGhastHolder;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(HappyGhastRenderState.class)
public class HappyGhastEntityRenderStateMixin implements HappyGhastHolder {
    @Unique private HappyGhast happyGhast;

    @Override
    public HappyGhast acornLib$getHappyGhast() {
        return this.happyGhast;
    }

    @Override
    public void acornLib$setHappyGhast(HappyGhast happyGhast) {
        this.happyGhast = happyGhast;
    }
}
