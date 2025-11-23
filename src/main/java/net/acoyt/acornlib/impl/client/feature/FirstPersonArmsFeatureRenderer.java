package net.acoyt.acornlib.impl.client.feature;

import net.acoyt.acornlib.api.event.RenderSkinLayerEvent;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Optional;

@Environment(EnvType.CLIENT)
public class FirstPersonArmsFeatureRenderer<T extends BipedEntityRenderState, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
    private final A model;

    public FirstPersonArmsFeatureRenderer(FeatureRendererContext<T, M> context, A model) {
        super(context);
        this.model = model;
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T renderState, float limbAngle, float limbDistance) {
        if (renderState instanceof PlayerEntityRenderStateAccess access && access.acornLib$getPlayerEntity() != null) {
            PlayerEntity player = access.acornLib$getPlayerEntity();
            boolean slim = ((PlayerEntityModelAccess)this.model).acornLib$isThinArms();
            for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
                if (stack.contains(AcornComponents.SKIN_LAYER)) {
                    if (SkinLayerComponent.hasStackInCorrectSlot(player, stack)) {
                        Identifier id = stack.getOrDefault(AcornComponents.SKIN_LAYER, SkinLayerComponent.DEFAULT).id();
                        Identifier modifiedId = Identifier.of(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim" : "") + ".png");
                        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(modifiedId));
                        VertexConsumer enchantBuffer = vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint());

                        //AcornLib.LOGGER.info(modifiedId.toString());

                        matrices.push();
                        matrices.scale(1.1F, 1.1F, 1.1F);
                        matrices.translate(0f, -0.04f, 0.0f);
                        this.getContextModel().copyTransforms(model);
                        model.setAngles(renderState);
                        this.renderModel(matrices, vertexConsumers, light, model, modifiedId);
                        //this.model.render(matrices, enchantBuffer, light, OverlayTexture.DEFAULT_UV, 0xFFFFFF);
                        if (stack.hasGlint())
                            this.model.render(matrices, enchantBuffer, light, OverlayTexture.DEFAULT_UV, 0xFFFFFF);
                        matrices.pop();
                    }
                }
            }

            if (player != null) {
                Optional<Identifier> identifier = RenderSkinLayerEvent.EVENT.invoker().getLayer(player);
                identifier.ifPresent(id -> {
                    Identifier modifiedId = Identifier.of(id.getNamespace(), "textures/entity/" + id.getPath() + (slim ? "_slim": "") + ".png");

                    matrices.push();
                    matrices.scale(1.1F, 1.1F, 1.1F);
                    matrices.translate(0f, -0.04f, 0.0f);
                    this.getContextModel().copyTransforms(model);
                    model.setAngles(renderState);
                    this.renderModel(matrices, vertexConsumers, light, model, modifiedId);
                    matrices.pop();
                });
            }
        }
    }

    private void renderModel(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, A model, Identifier identifier) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(identifier));
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
    }

    private void renderGlint(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, A model) {
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getArmorEntityGlint()), light, OverlayTexture.DEFAULT_UV);
    }
}
