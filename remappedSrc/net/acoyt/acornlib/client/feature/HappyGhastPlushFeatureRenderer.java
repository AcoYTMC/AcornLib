package net.acoyt.acornlib.client.feature;

import net.acoyt.acornlib.util.interfaces.HappyGhastHolder;
import net.acoyt.acornlib.util.interfaces.HappyGhastPlushHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.HappyGhastEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class HappyGhastPlushFeatureRenderer<M extends HappyGhastEntityModel> extends FeatureRenderer<HappyGhastEntityRenderState, M> {
    private final ItemRenderer itemRenderer;

    public HappyGhastPlushFeatureRenderer(FeatureRendererContext<HappyGhastEntityRenderState, M> context) {
        super(context);

        this.itemRenderer = MinecraftClient.getInstance().getItemRenderer();
    }

    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, HappyGhastEntityRenderState state, float limbAngle, float limbDistance) {
        float tickDelta = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        if (state instanceof HappyGhastHolder ghastHolder) {
            HappyGhastEntity happyGhast = ghastHolder.acornLib$getHappyGhast();
            if (happyGhast instanceof HappyGhastPlushHolder plushHolder) {
                ItemStack plushStack = plushHolder.acornLib$getPlushStack();
                if (!plushStack.isEmpty()) {
                    matrices.push();
                    matrices.translate(0, -3.11, 0);
                    matrices.scale(2.25F, 2.25F, 2.25F);
                    this.itemRenderer.renderItem(plushStack, ItemDisplayContext.FIXED, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, happyGhast.getWorld(), happyGhast.getId());
                    matrices.pop();
                }
            }
        }
    }
}
