package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CustomRiptideEvent;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.TridentRiptideFeatureRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(TridentRiptideFeatureRenderer.class)
public abstract class TridentRiptideFeatureRendererMixin<T extends LivingEntity> extends FeatureRenderer<T, PlayerEntityModel<T>> {
    @Shadow @Final private ModelPart aura;

    public TridentRiptideFeatureRendererMixin(FeatureRendererContext<T, PlayerEntityModel<T>> context) {
        super(context);
    }

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"
            ),
            cancellable = true
    )
    private void swapHotRiptide(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci) {
        if (livingEntity instanceof PlayerEntity player) {
            for (Hand hand : Hand.values()) {
                Optional<Identifier> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getStackInHand(hand));
                riptideTexture.ifPresent(texture -> {
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
                    ci.cancel();

                    for (int m = 0; m < 3; m++) {
                        matrixStack.push();
                        float n = j * -(45 + m * 5);
                        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(n));
                        float o = 0.75F * m;
                        matrixStack.scale(o, o, o);
                        matrixStack.translate(0.0F, -0.2F + 0.6F * m, 0.0F);
                        this.aura.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
                        matrixStack.pop();
                    }
                });
            }
        }
    }
}
