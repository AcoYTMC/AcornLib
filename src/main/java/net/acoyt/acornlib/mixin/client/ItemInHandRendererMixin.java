package net.acoyt.acornlib.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author AcoYT
 */
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm);

    @Inject(
            method = "renderArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 0
            )
    )
    public void acornlib$renderFirstPersonItem(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci) {
        if (itemStack.has(AcornDataComponents.SHOW_HAND)) {
            boolean bl = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();
            this.renderPlayerArm(poseStack, submitNodeCollector, lightCoords, inverseArmHeight, attack, arm);
            poseStack.popPose();
        }
    }
}
