package net.acoyt.acornlib.impl.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

//? if > 1.21.5 {
/*import net.minecraft.client.renderer.rendertype.RenderTypes;
*///? } else {
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderType;
//? }

/**
 * @author AcoYT
 */
//? > 1.21.5 {
/*public class PlaneModel extends Model.Simple {
    private final ModelPart bone;

    public PlaneModel(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        this.bone = root.getChild("bone");
    }
*///? } else {
public class PlaneModel extends Model {
    private final ModelPart bone;

    public PlaneModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.bone = root.getChild("bone");
    }

    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        bone.render(poseStack, vertexConsumer, light, overlay, color);
    }

    //? }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();
        PartDefinition bone = part.addOrReplaceChild(
                "bone",
                CubeListBuilder.create()
                        .texOffs(-16, 0)
                        .addBox(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(mesh, 16, 16);
    }
}
