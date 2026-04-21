package net.acoyt.acornlib.mixin.armPose;

import net.acoyt.acornlib.api.item.ArmPosableItem;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightArm;
    @Shadow @Final public ModelPart head;

    @Inject(method = "positionRightArm", at = @At("TAIL"))
    private void acornlib$armPosableRightArm(T entity, CallbackInfo ci) {
        if (entity.getMainHandStack().getItem() instanceof ArmPosableItem posableItem && entity.getMainArm() == Arm.RIGHT) {
            posableItem.positionArm(entity, this.rightArm, this.leftArm, this.head, true);
        }
    }

    @Inject(method = "positionLeftArm", at = @At("TAIL"))
    private void acornlib$armPosableLeftArm(T entity, CallbackInfo ci) {
        if (entity.getMainHandStack().getItem() instanceof ArmPosableItem posableItem && entity.getMainArm() != Arm.RIGHT) {
            posableItem.positionArm(entity, this.rightArm, this.leftArm, this.head, false);
        }
    }
}
