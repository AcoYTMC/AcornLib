package net.acoyt.acornlib.mixin.client.transparency;

import com.llamalad7.mixinextras.sugar.Local;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;" +
                    "Lnet/minecraft/text/Text;" +
                    "Lnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;IF)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$shadowWalkingNoLabel(AbstractClientPlayerEntity player, Text text, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, float f, CallbackInfo ci) {
        if (player.getAttributeValue(AcornAttributes.OPACITY) == 0.0F) {
            ci.cancel();
        }
    }

    @Redirect(
            method = "renderArm",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/ModelPart;render(Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;II)V"
            )
    )
    private void acornlib$redirectRender(ModelPart instance, MatrixStack matrices, VertexConsumer vertices, int light, int overlay, @Local(argsOnly = true) AbstractClientPlayerEntity player) {
        double opacity = player.getAttributeValue(AcornAttributes.OPACITY);
        if (opacity == 1.0) {
            instance.render(matrices, vertices, light, overlay);
            return;
        }

        int color = -1;
        int alpha = (int) (255 * opacity);
        int modifiedColor = (color & 0xFFFFFF) | (alpha << 24);
        instance.render(matrices, vertices, light, overlay, modifiedColor);
    }
}
