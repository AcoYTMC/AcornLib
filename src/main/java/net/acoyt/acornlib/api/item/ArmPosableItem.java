package net.acoyt.acornlib.api.item;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;

public interface ArmPosableItem {
    void positionArm(LivingEntity entity, ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed);
}
