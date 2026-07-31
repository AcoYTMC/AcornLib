package net.acoyt.acornlib.mixin.event;

import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.api.event.CustomRiptideEvent;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SpinAttackEffectLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

//? if > 1.21.1 {
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.rendertype.RenderType;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.client.addon.AvatarRenderStateAddon;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
//? } else {
/*import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
@Mixin(SpinAttackEffectLayer.class)
//? if > 1.21.1 {
public abstract class SpinAttackEffectLayerMixin extends RenderLayer<AvatarRenderState, PlayerModel> {
 //? } else {
/*public abstract class SpinAttackEffectLayerMixin<T extends LivingEntity> extends RenderLayer<T, PlayerModel<T>> {
    *///? }
    //? if <= 1.21.1
    //@Shadow @Final private ModelPart box;

    //? if > 1.21.1 {
    public SpinAttackEffectLayerMixin(RenderLayerParent<AvatarRenderState, PlayerModel> context) {
        super(context);
    }
    //? } else {
    /*public SpinAttackEffectLayerMixin(RenderLayerParent<T, PlayerModel<T>> renderLayerParent) {
        super(renderLayerParent);
    }
    *///? }

    //? if > 1.21.1 {
    @WrapOperation(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    //? if > 1.21.11 {
                    target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/resources/Identifier;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
                    //? } else {
                    /*target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
                    *///? }
            )
    )
    //? if > 1.21.11 {
    private void acornlib$swapHotRiptide(SubmitNodeCollector instance, Model<?> model, Object state, PoseStack poseStack, Identifier renderType, int lightCoords, int overlayCoords, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, Operation<Void> original) {
    //? } else {
    /*private void acornlib$swapHotRiptide(SubmitNodeCollector instance, Model<?> model, Object state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, Operation<Void> original) {
    *///? }
        if (state instanceof AvatarRenderState renderState) {
            AvatarRenderStateAddon addon = AvatarRenderStateAddon.get(renderState);
            if (addon.entity instanceof Player player) {
                for (InteractionHand hand : InteractionHand.values()) {
                    //? if > 1.21.11 {
                    Optional<Identifier> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getItemInHand(hand));
                    //? } else {
                    /*Optional<RenderType> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getItemInHand(hand));
                    *///? }
                    if (riptideTexture.isPresent()) {
                        original.call(instance, model, state, poseStack, riptideTexture.orElse(renderType), lightCoords, overlayCoords, outlineColor, crumblingOverlay);
                        return;
                    }
                }
            }
        }

        original.call(instance, model, state, poseStack, renderType, lightCoords, overlayCoords, outlineColor, crumblingOverlay);
    }
    //? } else {
    /*@Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/MultiBufferSource;getBuffer(Lnet/minecraft/client/renderer/RenderType;)Lcom/mojang/blaze3d/vertex/VertexConsumer;"
            ),
            cancellable = true
    )
    private void acornlib$swapHotRiptide(PoseStack poseStack, MultiBufferSource bufferSource, int i, LivingEntity livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (livingEntity instanceof Player player) {
            for (InteractionHand hand : InteractionHand.values()) {
                Optional<Identifier> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getItemInHand(hand));
                riptideTexture.ifPresent(texture -> {
                    VertexConsumer buffer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
                    ci.cancel();

                    for (int m = 0; m < 3; m++) {
                        poseStack.pushPose();
                        float n = j * -(45 + m * 5);
                        poseStack.mulPose(Axis.YP.rotationDegrees(n));
                        float o = 0.75F * m;
                        poseStack.scale(o, o, o);
                        poseStack.translate(0.0F, -0.2F + 0.6F * m, 0.0F);
                        this.box.render(poseStack, buffer, i, OverlayTexture.NO_OVERLAY);
                        poseStack.popPose();
                    }
                });
            }
        }
    }
    *///? }
}
