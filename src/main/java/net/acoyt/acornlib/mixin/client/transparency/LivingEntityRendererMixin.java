package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> {
    protected LivingEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @WrapOperation(
            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/EntityModel;render(Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;III)V"
            )
    )
    private void acornlib$redirectRender(M instance, MatrixStack matrixStack, VertexConsumer vertexConsumer, int light, int overlay, int color, Operation<Void> original, T entity) {
        if (entity instanceof PlayerEntity player) {
            double opacity = PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY));
            if (opacity >= 1.0) {
                original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
            original.call(instance, matrixStack, vertexConsumer, light, overlay, modifiedColor);
        } else {
            original.call(instance, matrixStack, vertexConsumer, light, overlay, color);
        }
    }

    @ModifyExpressionValue(
            method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;isSpectator()Z"
            )
    )
    private boolean acornlib$noFeatures(boolean original, T entity) {
        if (entity instanceof PlayerEntity player) {
            return original || PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY)) < 1.0;
        }

        return original;
    }
}
