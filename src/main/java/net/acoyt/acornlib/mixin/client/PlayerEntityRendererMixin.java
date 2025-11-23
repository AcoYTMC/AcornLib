package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.api.event.RenderSkinLayerEvent;
import net.acoyt.acornlib.impl.client.feature.FirstPersonArmsFeatureRenderer;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityRenderState, PlayerEntityModel> {
    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void addFeatures(EntityRendererFactory.Context ctx, boolean slim, CallbackInfo ci) {
        this.addFeature(new FirstPersonArmsFeatureRenderer<>(this, new PlayerEntityModel(ctx.getPart(slim ? EntityModelLayers.PLAYER_SLIM : EntityModelLayers.PLAYER), slim)));
        //this.addFeature(new TypeBasedRenderingCloneFeatureRenderer(this, new PlayerEntityModel(ctx.getPart(slim ? EntityModelLayers.PLAYER_SLIM : EntityModelLayers.PLAYER), slim)));
    }

    //@ModifyVariable(method = "renderArm", at = @At("HEAD"), argsOnly = true)
    //private Identifier modifiedArmSkin(Identifier original) {
    //    PlayerEntityModel playerEntityModel = this.getModel();
    //    if (playerEntityModel instanceof PlayerEntityModelAccess modelAccess) {
    //        if (modelAccess.acornLib$getRenderState() instanceof PlayerEntityRenderStateAccess stateAccess) {
    //            PlayerEntity player = stateAccess.acornLib$getPlayerEntity();
    //            for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
    //                if (stack.contains(AcornComponents.SKIN_OVERRIDE)) {
    //                    SkinOverrideComponent component = stack.getOrDefault(AcornComponents.SKIN_OVERRIDE, SkinOverrideComponent.DEFAULT);
    //                    if (SkinOverrideComponent.hasStackInCorrectSlot(player, stack)) {
    //                        modelAccess.acornLib$setThinArms(component.slim());
    //                        return component.id();
    //                    }
    //                }
    //            }
    //        }
    //    }
//
    //    return original;
    //}

    @Inject(method = "renderArm", at = @At("TAIL"))
    public void renderFeatures(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Identifier skinTexture, ModelPart arm, boolean sleeveVisible, CallbackInfo ci) {
        PlayerEntityModel playerEntityModel = this.getModel();
        if (playerEntityModel instanceof PlayerEntityModelAccess modelAccess) {
            boolean slim = modelAccess.acornLib$isThinArms();
            if (modelAccess.acornLib$getRenderState() instanceof PlayerEntityRenderStateAccess stateAccess) {
                PlayerEntity player = stateAccess.acornLib$getPlayerEntity();
                for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
                    if (stack.contains(AcornComponents.SKIN_LAYER)) {
                        if (SkinLayerComponent.hasStackInCorrectSlot(player, stack)) {
                            Identifier id = stack.getOrDefault(AcornComponents.SKIN_LAYER, SkinLayerComponent.DEFAULT).id();
                            Identifier modifiedId = Identifier.of(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim" : "") + ".png");
                            VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(modifiedId));

                            matrices.push();
                            matrices.scale(1.1F, 1.1F, 1.1F);
                            matrices.translate(0.0F, -0.4F, 0.0F);
                            arm.render(matrices, buffer, light, OverlayTexture.DEFAULT_UV);
                            matrices.pop();
                        }
                    }
                }

                if (player != null) {
                    Optional<Identifier> identifier = RenderSkinLayerEvent.EVENT.invoker().getLayer(player);
                    identifier.ifPresent(id -> {
                        Identifier modifiedId = Identifier.of(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim": "") + ".png");
                        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(modifiedId));

                        matrices.push();
                        matrices.scale(1.1F, 1.1F, 1.1F);
                        matrices.translate(0.0F, -0.4F, 0.0F);
                        arm.render(matrices, buffer, light, OverlayTexture.DEFAULT_UV);
                        matrices.pop();
                    });
                }
            }
        }
    }

    @Inject(
            method = "getArmPose(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/util/Arm;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void skylight$twoHandedPoses(AbstractClientPlayerEntity player, Arm arm, CallbackInfoReturnable<ArmPose> cir) {
        ItemStack stack = player.getStackInArm(arm);
        if (stack.contains(AcornComponents.TWO_HANDED)) {
            cir.setReturnValue(ArmPose.CROSSBOW_CHARGE);
        }

        if (stack.contains(AcornComponents.FOLLOWS_CAM)) {
            cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
        }
    }
}
