package net.acoyt.acornlib.api.item;

//? if > 1.21.4 {
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.4 {
public interface ArmPosableItem {
    void positionArm(HumanoidRenderState state, ModelPart holdingArm, ModelPart otherArm, ModelPart head, boolean rightArmed);

    static <T extends HumanoidRenderState> ItemStack getMainHandItemStack(T state) {
        return state.mainArm == HumanoidArm.RIGHT ? state.rightHandItemStack : state.leftHandItemStack;
    }

    static <T extends HumanoidRenderState> ItemStack getOffHandItemStack(T state) {
        return state.mainArm == HumanoidArm.RIGHT ? state.leftHandItemStack : state.rightHandItemStack;
    }
}
//? }