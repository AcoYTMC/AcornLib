package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if > 1.21.1 {
/*import net.acoyt.acornlib.impl.client.addon.HumanoidRenderStateAddon;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Shadow;
*///? } else {
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.world.entity.player.Player;
//? }

/**
 * @author AcoYT
 */
@Mixin(LivingEntityRenderer.class)
//? if > 1.21.1 {
/*public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> {
    @Shadow protected M model;

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @WrapOperation(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/texture/TextureAtlasSprite;ILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
            )
    )
    private <E> void acornlib$redirectRender(SubmitNodeCollector instance, Model<? super E> model, E state, PoseStack matrixStack, RenderType layer, int light, int overlay, int tintedColor, TextureAtlasSprite sprite, int outlineColor, ModelFeatureRenderer.CrumblingOverlay overlayCommand, Operation<Void> original) {
        if (state instanceof HumanoidRenderState renderState) {
            HumanoidRenderStateAddon addon = HumanoidRenderStateAddon.get(renderState);
            double opacity = addon.opacity;
            if (opacity >= 1.0) {
                original.call(instance, model, state, matrixStack, layer, light, overlay, tintedColor, sprite, outlineColor, overlayCommand);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (tintedColor & 0xFFFFFF) | (alpha << 24);
            original.call(instance, model, state, matrixStack, layer, light, overlay, modifiedColor, sprite, outlineColor, overlayCommand);
        } else {
            original.call(instance, model, state, matrixStack, layer, light, overlay, tintedColor, sprite, outlineColor, overlayCommand);
        }
    }

    @ModifyExpressionValue(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;shouldRenderLayers(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)Z"
            )
    )
    private boolean acornlib$noFeatures(boolean original, S state) {
        if (state instanceof HumanoidRenderState renderState) {
            HumanoidRenderStateAddon addon = HumanoidRenderStateAddon.get(renderState);
            return original && addon.opacity >= 1.0;
        }

        return original;
    }
}
*///? } else {
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {
    protected LivingEntityRendererMixin(EntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @WrapOperation(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;" +
                    "Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;III)V"
            )
    )
    private void acornlib$redirectRender(M instance, PoseStack poseStack, VertexConsumer buffer, int lightCoords, int overlayCoords, int color, Operation<Void> original, T entity) {
        if (entity instanceof Player player) {
            double opacity = PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY));
            if (opacity >= 1.0) {
                original.call(instance, poseStack, buffer, lightCoords, overlayCoords, color);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
            original.call(instance, poseStack, buffer, lightCoords, overlayCoords, modifiedColor);
        } else {
            original.call(instance, poseStack, buffer, lightCoords, overlayCoords, color);
        }
    }

    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;isSpectator()Z"
            )
    )
    private boolean acornlib$noFeatures(boolean original, T entity) {
        if (entity instanceof Player player) {
            return original || PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY)) < 1.0;
        }

        return original;
    }
}
//? }