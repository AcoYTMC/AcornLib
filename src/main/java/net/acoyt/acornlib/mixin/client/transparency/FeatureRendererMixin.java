package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.PlayerOpacityEvent;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FeatureRenderer.class)
public abstract class FeatureRendererMixin<T extends Entity, M extends EntityModel<T>> {
    @WrapOperation(
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
    private static <T extends LivingEntity> void acornlib$redirectRender(EntityModel<T> model, Identifier texture, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, int color, Operation<Void> original) {
        if (entity instanceof PlayerEntity player) {
            double opacity = PlayerOpacityEvent.EVENT.invoker().getOpacity(player).orElse(player.getAttributeValue(AcornAttributes.OPACITY));
            if (opacity >= 1.0) {
                original.call(model, texture, matrices, vertexConsumers, light, entity, color);
                return;
            }

            int alpha = (int) (255 * opacity);
            int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
            original.call(model, texture, matrices, vertexConsumers, light, entity, modifiedColor);
        } else {
            original.call(model, texture, matrices, vertexConsumers, light, entity, color);
        }
    }
}
