package net.acoyt.acornlib.impl.client.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastHolder;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastPlushHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

@Environment(EnvType.CLIENT)
public class HappyGhastPlushFeatureRenderer<M extends HappyGhastModel> extends RenderLayer<HappyGhastRenderState, M> {
    private final ItemRenderer itemRenderer;

    public HappyGhastPlushFeatureRenderer(RenderLayerParent<HappyGhastRenderState, M> context) {
        super(context);

        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
    }

    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, HappyGhastRenderState state, float limbAngle, float limbDistance) {
        float tickDelta = MiscUtils.tickDelta(MiscUtils.TickDeltaType.DEFAULT);
        if (state instanceof HappyGhastHolder ghastHolder) {
            HappyGhast happyGhast = ghastHolder.acornLib$getHappyGhast();
            if (happyGhast instanceof HappyGhastPlushHolder plushHolder) {
                ItemStack plushStack = plushHolder.acornLib$getPlushStack();
                if (!plushStack.isEmpty()) {
                    matrices.pushPose();
                    matrices.translate(0, -3, 0.3);
                    matrices.scale(1F, 1F, 1F);
                    matrices.mulPose(Axis.XP.rotationDegrees(180.0F));
                    matrices.mulPose(Axis.YP.rotationDegrees(180.0F));
                    this.itemRenderer.renderStatic(plushStack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, matrices, vertexConsumers, happyGhast.level(), happyGhast.getId());
                    matrices.popPose();
                }
            }
        }
    }
}