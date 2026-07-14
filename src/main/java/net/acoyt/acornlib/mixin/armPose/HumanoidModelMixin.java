package net.acoyt.acornlib.mixin.armPose;

import net.acoyt.acornlib.api.item.ArmPosableItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if > 1.21.1 {
/*import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
*///? } else {
import net.minecraft.world.entity.LivingEntity;
//? }

/**
 * @author AcoYT
 */
@Mixin(HumanoidModel.class)
//~ if > 1.21.1 'state.getMainArm()' -> 'state.mainArm' {
//~ if > 1.21.1 'LivingEntity' -> 'HumanoidRenderState'
public abstract class HumanoidModelMixin<T extends LivingEntity> {
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightArm;
    @Shadow @Final public ModelPart head;

    @Inject(method = "poseRightArm", at = @At("TAIL"))
    private void acornlib$armPosableRight(T state, CallbackInfo ci) {
        if (ArmPosableItem.getMainHandItemStack(state).getItem() instanceof ArmPosableItem posableItem && state.getMainArm() == HumanoidArm.RIGHT) {
            posableItem.positionArm(state, this.rightArm, this.leftArm, this.head, true);
        }
    }

    @Inject(method = "poseLeftArm", at = @At("TAIL"))
    private void acornlib$armPosableLeft(T state, CallbackInfo ci) {
        if (ArmPosableItem.getOffHandItemStack(state).getItem() instanceof ArmPosableItem posableItem && state.getMainArm() == HumanoidArm.RIGHT) {
            posableItem.positionArm(state, this.rightArm, this.leftArm, this.head, true);
        }
    }
}
//~ }