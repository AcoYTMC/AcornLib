package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {
    protected LivingEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Redirect(
            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;III)V"
            )
    )
    private void acornlib$redirectRender(M instance, MatrixStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, @Local(argsOnly = true) T entity) {
        double opacity = entity instanceof PlayerEntity player ? player.getAttributeValue(AcornAttributes.OPACITY) : 1.0;
        if (opacity == 1.0) {
            instance.render(matrixStack, vertexConsumer, light, overlay, color);
            return;
        }

        int alpha = (int) (255 * opacity);
        int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
        instance.render(matrixStack, vertexConsumer, light, overlay, modifiedColor);
    }

    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;features:Ljava/util/List;"
            )
    )
    private List<FeatureRenderer<T, M>> acornlib$noFeatures(List<FeatureRenderer<T, M>> original, T entity) {
        return entity instanceof PlayerEntity player && player.getAttributeValue(AcornAttributes.OPACITY) != 1.0 ? List.of() : original;
    }
}
