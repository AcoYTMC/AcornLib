package net.acoyt.acornlib.impl.client.feature;

import net.acoyt.acornlib.api.event.RenderSkinLayerEvent;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.Optional;

public class FirstPersonArmsFeatureRenderer<T extends HumanoidRenderState, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final A model;

    public FirstPersonArmsFeatureRenderer(RenderLayerParent<T, M> context, A model) {
        super(context);
        this.model = model;
    }

    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, T renderState, float limbAngle, float limbDistance) {
        if (renderState instanceof PlayerEntityRenderStateAccess access && access.acornLib$getPlayerEntity() != null) {
            Player player = access.acornLib$getPlayerEntity();
            boolean slim = ((PlayerEntityModelAccess)this.model).acornLib$isThinArms();
            for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
                if (stack.has(AcornComponents.SKIN_LAYER)) {
                    if (SkinLayerComponent.hasStackInCorrectSlot(player, stack)) {
                        ResourceLocation id = stack.getOrDefault(AcornComponents.SKIN_LAYER, SkinLayerComponent.DEFAULT).id();
                        ResourceLocation modifiedId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim" : "") + ".png");
                        VertexConsumer buffer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(modifiedId));
                        VertexConsumer enchantBuffer = vertexConsumers.getBuffer(RenderType.armorEntityGlint());

                        //AcornLib.LOGGER.info(modifiedId.toString());

                        matrices.pushPose();
                        matrices.scale(1.1F, 1.1F, 1.1F);
                        matrices.translate(0f, -0.04f, 0.0f);
                        this.getParentModel().copyPropertiesTo(model);
                        model.setupAnim(renderState);
                        this.renderModel(matrices, vertexConsumers, light, model, modifiedId);
                        //this.model.render(matrices, enchantBuffer, light, OverlayTexture.DEFAULT_UV, 0xFFFFFF);
                        if (stack.hasFoil())
                            this.model.renderToBuffer(matrices, enchantBuffer, light, OverlayTexture.NO_OVERLAY, 0xFFFFFF);
                        matrices.popPose();
                    }
                }
            }

            if (player != null) {
                Optional<ResourceLocation> identifier = RenderSkinLayerEvent.EVENT.invoker().getLayer(player);
                identifier.ifPresent(id -> {
                    ResourceLocation modifiedId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim": "") + ".png");

                    matrices.pushPose();
                    matrices.scale(1.1F, 1.1F, 1.1F);
                    matrices.translate(0f, -0.04f, 0.0f);
                    this.getParentModel().copyPropertiesTo(model);
                    model.setupAnim(renderState);
                    this.renderModel(matrices, vertexConsumers, light, model, modifiedId);
                    matrices.popPose();
                });
            }
        }
    }

    private void renderModel(PoseStack matrices, MultiBufferSource vertexConsumers, int light, A model, ResourceLocation identifier) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderType.entityCutoutNoCull(identifier));
        model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY);
    }

    private void renderGlint(PoseStack matrices, MultiBufferSource vertexConsumers, int light, A model) {
        model.renderToBuffer(matrices, vertexConsumers.getBuffer(RenderType.armorEntityGlint()), light, OverlayTexture.NO_OVERLAY);
    }
}
