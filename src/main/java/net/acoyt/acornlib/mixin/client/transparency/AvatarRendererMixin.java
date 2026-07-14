package net.acoyt.acornlib.mixin.client.transparency;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if > 1.21.1 {
/*import net.acoyt.acornlib.impl.client.addon.AvatarRenderStateAddon;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.entity.Avatar;
*///? } else {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.1 {
/*@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin<AvatarlikeEntity extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<AvatarlikeEntity, AvatarRenderState, PlayerModel> {
    public AvatarRendererMixin(EntityRendererProvider.Context ctx, PlayerModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            //~ if > 1.21.11 'submitNameTag' -> 'submitNameDisplay'
            method = "submitNameTag(Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$shadowWalkingNoLabel(AvatarRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera, CallbackInfo ci) {
        AvatarRenderStateAddon addon = AvatarRenderStateAddon.get(state);
        if (addon.opacity == 0.0) ci.cancel();
    }
}
*///? } else {
@Mixin(PlayerRenderer.class)
public abstract class AvatarRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public AvatarRendererMixin(EntityRendererProvider.Context ctx, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "renderNameTag(Lnet/minecraft/client/player/AbstractClientPlayer;" +
                    "Lnet/minecraft/network/chat/Component;Lcom/mojang/blaze3d/vertex/PoseStack;" +
                    "Lnet/minecraft/client/renderer/MultiBufferSource;IF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$shadowWalkingNoLabel(AbstractClientPlayer player, Component component, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, float f, CallbackInfo ci) {
        if (PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY)) == 0.0) {
            ci.cancel();
        }
    }

    @WrapOperation(
            method = "renderHand",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/geom/ModelPart;render(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;II)V"
            )
    )
    private void acornlib$redirectRender(ModelPart instance, PoseStack poseStack, VertexConsumer buffer, int lightCoords, int overlayCoords, Operation<Void> original, @Local(argsOnly = true) AbstractClientPlayer player) {
        double opacity = PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY));
        if (opacity == 1.0) {
            original.call(instance, poseStack, buffer, lightCoords, overlayCoords);
            return;
        }

        int color = -1;
        int alpha = (int) (255 * opacity);
        int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
        instance.render(poseStack, buffer, lightCoords, overlayCoords, modifiedColor);
    }
}
//? }