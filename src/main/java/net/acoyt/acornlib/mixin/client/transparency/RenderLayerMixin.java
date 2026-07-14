package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if > 1.21.1 {
/*import net.acoyt.acornlib.impl.client.addon.HumanoidRenderStateAddon;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
*///? } else {
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
//? }

/**
 * @author AcoYT
 */
@Mixin(RenderLayer.class)
//? if > 1.21.1 {
/*public abstract class RenderLayerMixin<S extends EntityRenderState, M extends EntityModel<? super S>> {
    @WrapOperation(
            method = "coloredCutoutModelCopyLayerRender(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;renderColoredCutoutModel(Lnet/minecraft/client/model/Model;Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;II)V"
            )
    )
    private static <S extends LivingEntityRenderState> void acornlib$redirectRender(Model<? super S> model, ResourceLocation texture, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, S state, int color, int order, Operation<Void> original) {
        if (state instanceof HumanoidRenderState renderState) {
            HumanoidRenderStateAddon addon = HumanoidRenderStateAddon.get(renderState);
            double opacity = addon.opacity;
            if (opacity >= 1.0) {
                original.call(model, texture, poseStack, submitNodeCollector, lightCoords, state, color, order);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
            original.call(model, texture, poseStack, submitNodeCollector, lightCoords, state, modifiedColor, order);
        } else {
            original.call(model, texture, poseStack, submitNodeCollector, lightCoords, state, color, order);
        }
    }
}
*///? } else {
public abstract class RenderLayerMixin<T extends Entity, M extends EntityModel<T>> {
    @WrapOperation(
            method = "coloredCutoutModelCopyLayerRender",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/layers/RenderLayer;renderColoredCutoutModel(Lnet/minecraft/client/model/EntityModel;" +
                            "Lnet/minecraft/resources/ResourceLocation;Lcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;I)V"
            )
    )
    private static <T extends LivingEntity> void acornlib$redirectRender(EntityModel<T> model, ResourceLocation texture, PoseStack poseStack, MultiBufferSource source, int light, T entity, int color, Operation<Void> original) {
        if (entity instanceof Player player) {
            double opacity = PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY));
            if (opacity >= 1.0) {
                original.call(model, texture, poseStack, source, light, entity, color);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
            original.call(model, texture, poseStack, source, light, entity, modifiedColor);
        } else {
            original.call(model, texture, poseStack, source, light, entity, color);
        }
    }
}
//? }