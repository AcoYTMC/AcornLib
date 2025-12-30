package net.acoyt.acornlib.impl.client;

import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.acoyt.acornlib.mixin.client.BlockRenderManagerAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class PlushBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final BlockRenderManager renderManager;

    public PlushBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context ctx) {
        this.renderManager = ctx.getRenderManager();
    }

    @Override
    public void render(@NotNull T entity, float tickDelta, @NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        var squish = entity instanceof PlushBlockEntity plush ? plush.squish : 0;
        var prevSquish = squish * 3;
        var squish2 = (float) Math.pow(1 - 1f / (1f + MathHelper.lerp(tickDelta, prevSquish, squish)), 2);
        matrices.scale(1, 1 - squish2, 1);
        matrices.translate(0.5, 0, 0.5);
        matrices.scale(1 + squish2 / 2, 1, 1 + squish2 / 2);
        matrices.translate(-0.5, 0, -0.5);
        var state = entity.getCachedState();
        var bakedModel = this.renderManager.getModel(state);
        ((BlockRenderManagerAccessor) this.renderManager).getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayers.getEntityBlockLayer(state, false)), state, bakedModel, 0xFF, 0xFF, 0xFF, light, overlay);
        matrices.pop();
    }
}
