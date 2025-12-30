package net.acoyt.acornlib.mixin.armPose;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.item.CustomArmPoseItem;
import net.acoyt.acornlib.impl.client.armPose.CustomArmPose;
import net.acoyt.acornlib.impl.client.armPose.CustomArmPosing;
import net.acoyt.acornlib.impl.client.armPose.IArmPose;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> implements ModelWithArms, ModelWithHead {
    @Shadow @Final public ModelPart head;
    @Shadow @Final public ModelPart body;
    @Shadow @Final public ModelPart leftArm;
    @Shadow @Final public ModelPart rightArm;
    @Shadow protected abstract Arm getPreferredArm(T entity);
    @Shadow protected abstract ModelPart getArm(Arm arm);
    @Unique private boolean preventLimbSwing = false;

    @Inject(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;animateArms(Lnet/minecraft/entity/LivingEntity;F)V"))
    private void applyArmTransformations(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = CustomArmPoseItem.getWeapon(entity);
        if (stack != null) {
            IArmPose mainPose = ((CustomArmPoseItem) stack.getItem()).getMainHandPose(entity, stack);
            IArmPose otherPose = ((CustomArmPoseItem) stack.getItem()).getOffHandPose(entity, stack);
            CustomArmPosing.positionLeftArm(entity, otherPose, this.leftArm, this.head.pitch, this.head.yaw, this.body.pitch, this.body.yaw, entity.getMainArm() == Arm.LEFT);
            CustomArmPosing.positionRightArm(entity, mainPose, this.rightArm, this.head.pitch, this.head.yaw, this.body.pitch, this.body.yaw, entity.getMainArm() == Arm.LEFT);

            this.preventLimbSwing = (mainPose instanceof CustomArmPose && ((CustomArmPose) mainPose).preventLimbSwing())
                    || (otherPose instanceof CustomArmPose && ((CustomArmPose) otherPose).preventLimbSwing());
        }
    }

    @WrapOperation(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/BipedEntityModel;animateArms(Lnet/minecraft/entity/LivingEntity;F)V"))
    private void preventAttackAnimations(BipedEntityModel<?> model, T entity, float animationProgress, Operation<Void> original) {
        ItemStack stack = entity.getOffHandStack();
        if (entity.getMainHandStack().isEmpty() && stack.getItem() instanceof CustomArmPoseItem weapon) {
            IArmPose pose = weapon.getCustomPose(entity, stack);
            if (pose != null && pose.twoHanded()) {
                this.handSwingProgress = 0.0f;
            }
        }

        original.call(model, entity, animationProgress);
    }

    @WrapWithCondition(method = "setAngles(Lnet/minecraft/entity/LivingEntity;FFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/model/CrossbowPosing;swingArm(Lnet/minecraft/client/model/ModelPart;FF)V"))
    private boolean preventArmSway(ModelPart arm, float animationProgress, float sigma) {
        boolean bl = this.preventLimbSwing;
        this.preventLimbSwing = false;
        return !bl;
    }

    @Inject(method = "animateArms", at = @At(value = "TAIL"))
    private void twoHandedAttack(T entity, float animationProgress, CallbackInfo ci) {
        ItemStack stack = entity.getMainHandStack();
        if (stack.getItem() instanceof CustomArmPoseItem weapon && this.handSwingProgress > 0.0f) {

            IArmPose pose = weapon.getCustomPose(entity, stack);
            if (pose != null && pose.twoHanded()) {
                ModelPart arm = this.getArm(this.getPreferredArm(entity).getOpposite());

                float f = 1.0f - this.handSwingProgress;
                f *= f;
                f *= f;
                f = 1.0f - f;
                float g = MathHelper.sin(f * (float) Math.PI);
                float h = MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -(this.head.pitch - 0.7f) * 0.75f;
                arm.pitch -= g * 1.2f + h;
                arm.yaw = arm.yaw + this.body.yaw * 2.0f;
                arm.roll = arm.roll + MathHelper.sin(this.handSwingProgress * (float) Math.PI) * -0.4f;
            }
        }
    }
}
