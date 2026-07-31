package net.acoyt.acornlib.mixin.client;

//~ if > 1.21.1 'MultiBufferSource bufferSource' -> 'SubmitNodeCollector collector' {
//~ if > 1.21.1 'bufferSource' -> 'collector' {
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if > 1.21.1 {
import net.minecraft.client.renderer.SubmitNodeCollector;
//? } else {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///? }

/**
 * @author AcoYT
 */
@Mixin(ItemInHandRenderer.class)
public abstract class ItemInHandRendererMixin {
    @Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm);

    @Inject(
            method = "renderArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 0
            )
    )
    public void acornlib$renderFirstPersonItem(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, CallbackInfo ci) {
        if (itemStack.has(AcornDataComponents.SHOW_HAND)) {
            boolean bl = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();
            this.renderPlayerArm(poseStack, collector, lightCoords, inverseArmHeight, attack, arm);
            poseStack.popPose();
        }
    }
}
//~ }
//~ }