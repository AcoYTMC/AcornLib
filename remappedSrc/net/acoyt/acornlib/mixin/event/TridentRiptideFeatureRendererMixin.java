package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.CustomRiptideEvent;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.model.SpinAttackEffectModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.SpinAttackEffectLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Optional;

@Mixin(SpinAttackEffectLayer.class)
public abstract class TridentRiptideFeatureRendererMixin {
    @Shadow @Final private SpinAttackEffectModel model;

    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/PlayerEntityRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/VertexConsumerProvider;getBuffer(Lnet/minecraft/client/render/RenderLayer;)Lnet/minecraft/client/render/VertexConsumer;"
            ),
            cancellable = true
    )
    private void swapHotRiptide(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, PlayerRenderState renderState, float f, float g, CallbackInfo ci) {
        if (renderState instanceof PlayerEntityRenderStateAccess access && renderState.isAutoSpinAttack) {
            Player player = access.acornLib$getPlayerEntity();
            if (player != null) {
                for (InteractionHand hand : InteractionHand.values()) {
                    Optional<ResourceLocation> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getItemInHand(hand));
                    riptideTexture.ifPresent(texture -> {
                        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderType.entityCutoutNoCull(texture));
                        ci.cancel();
                        this.model.setupAnim(renderState);
                        this.model.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
                    });
                }
            }
        }
    }
}
