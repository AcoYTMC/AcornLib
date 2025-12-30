package net.acoyt.acornlib.mixin.armPose;

import net.acoyt.acornlib.api.item.CustomArmPoseItem;
import net.acoyt.acornlib.impl.client.armPose.IArmPose;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void customVanillaArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack stack = player.getMainHandStack().getItem() instanceof CustomArmPoseItem ? player.getMainHandStack() : player.getOffHandStack();

        if (stack.getItem() instanceof CustomArmPoseItem weapon) {
            IArmPose mainPose = weapon.getMainHandPose(player, stack);
            IArmPose otherPose = weapon.getOffHandPose(player, stack);

            if (hand == Hand.MAIN_HAND && mainPose != null && mainPose.value() == IArmPose.Value.VANILLA) {
                cir.setReturnValue((BipedEntityModel.ArmPose) mainPose);
            }

            if (hand == Hand.OFF_HAND && otherPose != null && otherPose.value() == IArmPose.Value.VANILLA) {
                cir.setReturnValue((BipedEntityModel.ArmPose) otherPose);
            }
        }
    }
}
