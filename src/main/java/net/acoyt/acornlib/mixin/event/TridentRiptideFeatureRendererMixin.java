package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CustomRiptideEvent;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.TridentRiptideFeatureRenderer;
import net.minecraft.client.render.entity.model.TridentRiptideEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(TridentRiptideFeatureRenderer.class)
public abstract class TridentRiptideFeatureRendererMixin {
    @Shadow @Final private TridentRiptideEntityModel model;

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/PlayerEntityRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"
            ),
            cancellable = true
    )
    private void swapHotRiptide(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, PlayerEntityRenderState renderState, float f, float g, CallbackInfo ci) {
        if (renderState instanceof PlayerEntityRenderStateAccess access && renderState.usingRiptide) {
            PlayerEntity player = access.acornLib$getPlayerEntity();
            if (player != null) {
                for (Hand hand : Hand.values()) {
                    Optional<Identifier> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getStackInHand(hand));
                    riptideTexture.ifPresent(texture -> {
                        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(texture));
                        ci.cancel();
                        this.model.setAngles(renderState);
                        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
                    });
                }
            }
        }
    }
}
