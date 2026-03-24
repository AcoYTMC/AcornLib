package net.acoyt.acornlib.mixin.client.transparency;

import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FeatureRenderer.class)
public abstract class FeatureRendererMixin<T extends Entity, M extends EntityModel<T>> {
    @Redirect(
            method = "render(Lnet/minecraft/client/render/entity/model/EntityModel;" +
                    "Lnet/minecraft/client/render/entity/model/EntityModel;" +
                    "Lnet/minecraft/util/Identifier;" +
                    "Lnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFFI)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/feature/FeatureRenderer;renderModel(Lnet/minecraft/client/render/entity/model/EntityModel;" +
                            "Lnet/minecraft/util/Identifier;" +
                            "Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;I)V"
            )
    )
    private static <T extends LivingEntity> void acornlib$redirectRender(EntityModel<T> model, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, int i) {
        double opacity = entity instanceof PlayerEntity player ? player.getAttributeValue(AcornAttributes.OPACITY) : 1.0;
        if (opacity == 1.0) {
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
            model.render(matrices, vertexConsumer, light, LivingEntityRenderer.getOverlay(entity, 0.0F), i);
            return;
        }

        int alpha = (int) (255 * opacity);
        int modifiedColor = (i & 0xFFFFFF) | (alpha << 24);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
        model.render(matrices, vertexConsumer, light, LivingEntityRenderer.getOverlay(entity, 0.0F), modifiedColor);
    }
}
