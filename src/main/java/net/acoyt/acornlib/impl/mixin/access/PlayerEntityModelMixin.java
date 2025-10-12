package net.acoyt.acornlib.impl.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntityModel.class)
public class PlayerEntityModelMixin extends BipedEntityModel<PlayerEntityRenderState> implements PlayerEntityModelAccess {
    @Unique private PlayerEntityRenderState renderState;
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
    public PlayerEntityRenderState acornLib$getRenderState() {
        return this.renderState;
    }

    @Override
    public void acornLib$setRenderState(PlayerEntityRenderState renderState) {
        this.renderState = renderState;
    }
}
