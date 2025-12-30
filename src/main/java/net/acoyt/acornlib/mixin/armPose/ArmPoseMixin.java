package net.acoyt.acornlib.mixin.armPose;

import net.acoyt.acornlib.impl.client.armPose.IArmPose;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BipedEntityModel.ArmPose.class)
public abstract class ArmPoseMixin implements IArmPose {
    @Shadow public abstract boolean isTwoHanded();

    @Override
    public boolean twoHanded() {
        return this.isTwoHanded();
    }

    @Override
    public Value value() {
        return Value.VANILLA;
    }
}
