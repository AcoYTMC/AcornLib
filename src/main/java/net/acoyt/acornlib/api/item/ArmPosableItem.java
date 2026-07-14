package net.acoyt.acornlib.api.item;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;

//? if > 1.21.1 {
/*import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
 *///? } else {
import net.minecraft.world.entity.LivingEntity;
//? }

/**
 * @author AcoYT
 */
//~ if > 1.21.1 'LivingEntity' -> 'HumanoidRenderState' {
public interface ArmPosableItem {
    void positionArm(LivingEntity state, ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed);

    //? if > 1.21.1 {
    /*static <T extends LivingEntity> ItemStack getMainHandItemStack(T state) {
        return state.mainArm == HumanoidArm.RIGHT ? state.rightHandItemStack : state.leftHandItemStack;
    }

    static <T extends LivingEntity> ItemStack getOffHandItemStack(T state) {
        return state.mainArm == HumanoidArm.RIGHT ? state.leftHandItemStack : state.rightHandItemStack;
    }
    *///? } else {
    static <T extends LivingEntity> ItemStack getMainHandItemStack(T state) {
        ItemStack rightHandItemStack = state.getMainArm() == HumanoidArm.RIGHT ? state.getMainHandItem() : state.getOffhandItem();
        ItemStack leftHandItemStack = state.getMainArm() == HumanoidArm.LEFT ? state.getMainHandItem() : state.getOffhandItem();
        return state.getMainArm() == HumanoidArm.RIGHT ? rightHandItemStack : leftHandItemStack;
    }

    static <T extends LivingEntity> ItemStack getOffHandItemStack(T state) {
        ItemStack rightHandItemStack = state.getMainArm() == HumanoidArm.RIGHT ? state.getMainHandItem() : state.getOffhandItem();
        ItemStack leftHandItemStack = state.getMainArm() == HumanoidArm.LEFT ? state.getMainHandItem() : state.getOffhandItem();
        return state.getMainArm() == HumanoidArm.RIGHT ? leftHandItemStack : rightHandItemStack;
    }
    //? }
}
//~ }