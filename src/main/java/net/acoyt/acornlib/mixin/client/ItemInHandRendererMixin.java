package net.acoyt.acornlib.mixin.client;

//~ if > 1.21.1 'MultiBufferSource bufferSource' -> 'SubmitNodeCollector collector' {
//~ if > 1.21.1 'bufferSource' -> 'collector' {
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
//~ if > 26.1.2 'ItemInHandRenderer' -> 'FirstPersonHandsAndItemsRenderer'
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if > 26.1.2 {
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
//? }

//? if <= 26.1.2
//import net.minecraft.client.player.AbstractClientPlayer;

//? if > 1.21.1 {
import net.minecraft.client.renderer.SubmitNodeCollector;
//? } else {
/*import net.minecraft.client.renderer.MultiBufferSource;
 *///? }

/**
 * @author AcoYT
 */
//~ if > 26.1.2 'ItemInHandRenderer' -> 'FirstPersonHandsAndItemsRenderer'
@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class ItemInHandRendererMixin {
    //? if > 26.1.2 {
    @Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm, PlayerRenderState playerState);
     //? } else {
    /*@Shadow protected abstract void renderPlayerArm(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, float inverseArmHeight, float attackValue, HumanoidArm arm);
    *///? }

    @Inject(
            //~ if > 26.1.2 'render' -> 'submit'
            method = "submitArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 0
            )
    )
    //? if > 26.1.2 {
    public void acornlib$renderFirstPersonItem(PlayerRenderState playerState, FirstPersonHandsAndItemsRenderState state, float partialTicks, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, CallbackInfo ci) {
        AvatarRenderState renderState = playerState.avatarRenderState;
        if (itemStack.has(AcornDataComponents.SHOW_HAND) && renderState != null) {
            HumanoidArm mainArm = renderState.mainArm;

            boolean bl = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = bl ? mainArm : mainArm.getOpposite();
            poseStack.pushPose();
            this.renderPlayerArm(poseStack, submitNodeCollector, lightCoords, inverseArmHeight, attack, arm, playerState);
            poseStack.popPose();
        }
    }
    //? } else {
    /*public void acornlib$renderFirstPersonItem(AbstractClientPlayer player, float frameInterp, float xRot, InteractionHand hand, float attack, ItemStack itemStack, float inverseArmHeight, PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, CallbackInfo ci) {
        if (itemStack.has(AcornDataComponents.SHOW_HAND)) {
            boolean bl = hand == InteractionHand.MAIN_HAND;
            HumanoidArm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
            poseStack.pushPose();
            this.renderPlayerArm(poseStack, collector, lightCoords, inverseArmHeight, attack, arm);
            poseStack.popPose();
        }
    }
    *///? }
}
//~ }
//~ }