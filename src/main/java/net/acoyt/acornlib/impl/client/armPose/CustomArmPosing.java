package net.acoyt.acornlib.impl.client.armPose;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;

public class CustomArmPosing {
    public static <T extends LivingEntity> void positionLeftArm(T entity, IArmPose pose, ModelPart leftArm, float headPitch, float headYaw, float bodyPitch, float bodyYaw, boolean leftHanded) {
        if (pose != null && pose.value() != IArmPose.Value.VANILLA) {
            if (pose instanceof CustomArmPose armPose) {
                armPose.transformLeft(leftArm, new CustomArmPose.Context(entity, headPitch, headYaw, bodyPitch, bodyYaw, leftHanded));
            }
        }
    }

    public static <T extends LivingEntity> void positionRightArm(T entity, IArmPose pose, ModelPart rightArm, float headPitch, float headYaw, float bodyPitch, float bodyYaw, boolean leftHanded) {
        if (pose != null && pose.value() != IArmPose.Value.VANILLA) {
            if (pose instanceof CustomArmPose armPose) {
                armPose.transformRight(rightArm, new CustomArmPose.Context(entity, headPitch, headYaw, bodyPitch, bodyYaw, leftHanded));
            }
        }
    }
}
