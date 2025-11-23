package net.acoyt.acornlib.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerModel.class)
public class PlayerEntityModelMixin extends HumanoidModel<PlayerRenderState> implements PlayerEntityModelAccess {
    @Unique private PlayerRenderState renderState;
    //@Mutable
    @Shadow @Final public boolean thinArms;

    public PlayerEntityModelMixin(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public boolean acornLib$isThinArms() {
        return this.thinArms;
    }

    //@Override
    //public void acornLib$setThinArms(boolean thinArms) {
    //    this.thinArms = thinArms;
    //}

    @Override
    public PlayerRenderState acornLib$getRenderState() {
        return this.renderState;
    }

    @Override
    public void acornLib$setRenderState(PlayerRenderState renderState) {
        this.renderState = renderState;
    }
}
