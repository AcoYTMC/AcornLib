package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.client.armPose.IArmPose;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface CustomArmPoseItem {
    /// Returns the pose for the holder's main hand when this item is held.
    default IArmPose getMainHandPose(LivingEntity holder, ItemStack stack) {
        return holder.getMainHandStack() == stack ? BipedEntityModel.ArmPose.ITEM : BipedEntityModel.ArmPose.EMPTY;
    }

    /// Returns the pose for the holder's offhand when this item is held.
    default IArmPose getOffHandPose(LivingEntity holder, ItemStack stack) {
        return holder.getOffHandStack() == stack ? BipedEntityModel.ArmPose.ITEM : BipedEntityModel.ArmPose.EMPTY;
    }

    /// Returns any custom pose which this item might apply to the holder. Prioritizes the main hand.
    default IArmPose getCustomPose(LivingEntity holder, ItemStack stack) {
        IArmPose mainPose = getMainHandPose(holder, stack);
        IArmPose otherPose = getOffHandPose(holder, stack);

        if (mainPose != null && isCustom(mainPose)) {
            return mainPose;
        } else if (otherPose != null && isCustom(otherPose)) {
            return otherPose;
        }

        return null;
    }

    static IArmPose getCustomPose(LivingEntity holder) {
        ItemStack stack = getWeapon(holder);
        if (stack != null) {
            return ((CustomArmPoseItem) stack.getItem()).getCustomPose(holder, stack);
        }
        return null;
    }

    /// Returns any CustomArmPoseItem which the holder might be holding. Prioritizes the main hand.
    static ItemStack getWeapon(LivingEntity holder) {
        if (holder.getMainHandStack().getItem() instanceof CustomArmPoseItem) {
            return holder.getMainHandStack();
        } else if (holder.getOffHandStack().getItem() instanceof CustomArmPoseItem) {
            return holder.getOffHandStack();
        } else {
            return null;
        }
    }

    static boolean isCustom(IArmPose pose) {
        return pose.value() != IArmPose.Value.VANILLA || (pose != BipedEntityModel.ArmPose.EMPTY && pose != BipedEntityModel.ArmPose.ITEM);
    }
}
