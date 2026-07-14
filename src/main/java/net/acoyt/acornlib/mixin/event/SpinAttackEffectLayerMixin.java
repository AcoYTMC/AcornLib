package net.acoyt.acornlib.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.api.event.CustomRiptideEvent;
import net.acoyt.acornlib.impl.client.addon.AvatarRenderStateAddon;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.SpinAttackEffectLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

//? if > 1.21.11 {
/*import net.minecraft.resources.ResourceLocation;
 *///? } else {
import net.minecraft.client.renderer.rendertype.RenderType;
//? }

/**
 * @author AcoYT
 */
@Mixin(SpinAttackEffectLayer.class)
public abstract class SpinAttackEffectLayerMixin extends RenderLayer<AvatarRenderState, PlayerModel> {
    public SpinAttackEffectLayerMixin(RenderLayerParent<AvatarRenderState, PlayerModel> context) {
        super(context);
    }

    @WrapOperation(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
            at = @At(
                    value = "INVOKE",
                    //? if > 1.21.11 {
                    /*target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/resources/ResourceLocation;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
                    *///? } else {
                    target = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lcom/mojang/blaze3d/vertex/PoseStack;" +
            "Lnet/minecraft/client/renderer/rendertype/RenderType;IIILnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V"
                    //? }
            )
    )
    //~ if > 1.21.11 'renderType' -> 'identifier' {
    //~ if > 1.21.11 'RenderType' -> 'Identifier' {
    private void acornlib$swapHotRiptide(SubmitNodeCollector instance, Model<?> model, Object state, PoseStack poseStack, RenderType renderType, int lightCoords, int overlayCoords, int outlineColor, ModelFeatureRenderer.CrumblingOverlay crumblingOverlay, Operation<Void> original) {
        if (state instanceof AvatarRenderState renderState) {
            AvatarRenderStateAddon addon = AvatarRenderStateAddon.get(renderState);
            if (addon.entity instanceof Player player) {
                for (InteractionHand hand : InteractionHand.values()) {
                    Optional<RenderType> riptideTexture = CustomRiptideEvent.EVENT.invoker().getRiptideTexture(player, player.getItemInHand(hand));
                    if (riptideTexture.isPresent()) {
                        original.call(instance, model, state, poseStack, riptideTexture.orElse(renderType), lightCoords, overlayCoords, outlineColor, crumblingOverlay);
                        return;
                    }
                }
            }
        }

        original.call(instance, model, state, poseStack, renderType, lightCoords, overlayCoords, outlineColor, crumblingOverlay);
    }
    //~ }
    //~ }
}
