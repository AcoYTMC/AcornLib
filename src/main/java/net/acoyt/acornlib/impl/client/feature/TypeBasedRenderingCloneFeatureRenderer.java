package net.acoyt.acornlib.impl.client.feature;

import net.acoyt.acornlib.api.event.SkinOverrideEvent;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.component.SkinOverrideComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.event.SkinOverrideEvent.Type.*;

public class TypeBasedRenderingCloneFeatureRenderer extends FeatureRenderer<PlayerEntityRenderState, PlayerEntityModel> {
    //private final PlayerEntityModel leftArm;
    //private final PlayerEntityModel leftSleeve;
    //private final PlayerEntityModel rightArm;
    //private final PlayerEntityModel rightSleeve;
    //private final PlayerEntityModel body;
    //private final PlayerEntityModel jacket;
    //private final PlayerEntityModel leftLeg;
    //private final PlayerEntityModel leftPants;
    //private final PlayerEntityModel rightLeg;
    //private final PlayerEntityModel rightPants;
    //private final PlayerEntityModel head;
    //private final PlayerEntityModel hat;

    public TypeBasedRenderingCloneFeatureRenderer(FeatureRendererContext<PlayerEntityRenderState, PlayerEntityModel> context, PlayerEntityModel model) {
        super(context);
        //this.leftArm = new PlayerEntityModel(context.getModel().leftArm, getContextModel().thinArms);
        //this.leftSleeve = new PlayerEntityModel(context.getModel().leftSleeve, model.thinArms);
        //this.rightArm = new PlayerEntityModel(context.getModel().rightArm, model.thinArms);
        //this.rightSleeve = new PlayerEntityModel(context.getModel().rightSleeve, model.thinArms);
        //this.body = new PlayerEntityModel(context.getModel().body, model.thinArms);
        //this.jacket = new PlayerEntityModel(context.getModel().body, model.thinArms);
        //this.leftLeg = new PlayerEntityModel(context.getModel().leftLeg, model.thinArms);
        //this.leftPants = new PlayerEntityModel(context.getModel().leftPants, model.thinArms);
        //this.rightLeg = new PlayerEntityModel(context.getModel().rightLeg, model.thinArms);
        //this.rightPants = new PlayerEntityModel(context.getModel().rightPants, model.thinArms);
        //this.head = new PlayerEntityModel(context.getModel().getRootPart().getChild("head"), model.thinArms);
        //this.hat = new PlayerEntityModel(context.getModel().hat, model.thinArms);
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityRenderState renderState, float limbAngle, float limbDistance) {
        //if (renderState instanceof PlayerEntityRenderStateAccess stateAccess) {
        //    PlayerEntity player = stateAccess.acornLib$getPlayerEntity();
        //    for (ItemStack stack : SkinLayerComponent.getEquippedStacks(player)) {
        //        if (stack.contains(AcornComponents.SKIN_OVERRIDE)) {
        //            SkinOverrideComponent component = stack.getOrDefault(AcornComponents.SKIN_OVERRIDE, SkinOverrideComponent.DEFAULT);
        //            if (SkinOverrideComponent.hasStackInCorrectSlot(player, stack)) {
        //                Identifier id = component.id();
        //                SkinOverrideEvent.Type type = component.type();

        //                // Left Arm
        //                if (type == NOT_HEAD || type == TORSO || type == ARMS) {
        //                    this.getContextModel().copyTransforms(leftArm);
        //                    leftArm.setAngles(renderState);
        //                    this.renderModel(matrices, vertexConsumers, light, leftArm, id);
        //
        //                    this.getContextModel().copyTransforms(leftSleeve);
        //                    leftSleeve.setAngles(renderState);
        //                    this.renderModel(matrices, vertexConsumers, light, leftSleeve, id);
        //                }
        //            }
        //        }
        //    }
        //}
    }

    private void renderModel(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, PlayerEntityModel model, Identifier id) {
        model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(id)), light, OverlayTexture.DEFAULT_UV);
    }
}
