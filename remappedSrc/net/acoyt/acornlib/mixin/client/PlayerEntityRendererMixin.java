package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.api.event.RenderSkinLayerEvent;
import net.acoyt.acornlib.impl.client.feature.FirstPersonArmsFeatureRenderer;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Optional;

@Mixin(PlayerRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerRenderState, PlayerModel> {
    public PlayerEntityRendererMixin(EntityRendererProvider.Context ctx, PlayerModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void addFeatures(EntityRendererProvider.Context ctx, boolean slim, CallbackInfo ci) {
        this.addLayer(new FirstPersonArmsFeatureRenderer<>(this, new PlayerModel(ctx.bakeLayer(slim ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), slim)));
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
    public void renderFeatures(PoseStack matrices, MultiBufferSource vertexConsumers, int light, ResourceLocation skinTexture, ModelPart arm, boolean sleeveVisible, CallbackInfo ci) {
        PlayerModel playerEntityModel = this.getModel();
        if (playerEntityModel instanceof PlayerEntityModelAccess modelAccess) {
            boolean slim = modelAccess.acornLib$isThinArms();
            if (modelAccess.acornLib$getRenderState() instanceof PlayerEntityRenderStateAccess stateAccess) {
                Player player = stateAccess.acornLib$getPlayerEntity();
                for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
                    if (stack.has(AcornComponents.SKIN_LAYER)) {
                        if (SkinLayerComponent.hasStackInCorrectSlot(player, stack)) {
                            ResourceLocation id = stack.getOrDefault(AcornComponents.SKIN_LAYER, SkinLayerComponent.DEFAULT).id();
                            ResourceLocation modifiedId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim" : "") + ".png");
                            VertexConsumer buffer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(modifiedId));

                            matrices.pushPose();
                            matrices.scale(1.1F, 1.1F, 1.1F);
                            matrices.translate(0f, -0.04f, 0.0f);
                            arm.render(matrices, buffer, light, OverlayTexture.NO_OVERLAY);
                            matrices.popPose();
                        }
                    }
                }

                if (player != null) {
                    Optional<ResourceLocation> identifier = RenderSkinLayerEvent.EVENT.invoker().getLayer(player);
                    identifier.ifPresent(id -> {
                        ResourceLocation modifiedId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim": "") + ".png");
                        VertexConsumer buffer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(modifiedId));

                        matrices.pushPose();
                        matrices.scale(1.1F, 1.1F, 1.1F);
                        matrices.translate(0f, -0.04f, 0.0f);
                        arm.render(matrices, buffer, light, OverlayTexture.NO_OVERLAY);
                        matrices.popPose();
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
    private static void skylight$twoHandedPoses(AbstractClientPlayer player, HumanoidArm arm, CallbackInfoReturnable<ArmPose> cir) {
        ItemStack stack = player.getItemHeldByArm(arm);
        if (stack.has(AcornComponents.TWO_HANDED)) {
            cir.setReturnValue(ArmPose.CROSSBOW_CHARGE);
        }

        if (stack.has(AcornComponents.FOLLOWS_CAM)) {
            cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
        }
    }
}
