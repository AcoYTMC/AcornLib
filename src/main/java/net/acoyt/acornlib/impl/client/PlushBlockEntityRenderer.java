package net.acoyt.acornlib.impl.client;

import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Environment(EnvType.CLIENT)
public class PlushBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final BlockRenderManager renderManager;

    public PlushBlockEntityRenderer(BlockEntityRendererFactory.@NotNull Context ctx) {
        this.renderManager = ctx.getRenderManager();
    }

    @Override
    public void render(T entity, float tickProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, Vec3d cameraPos) {
        matrices.push();
        double squish = entity instanceof PlushBlockEntity plush ? plush.squish : 0;
        double prevSquish = squish * 3;
        float squish2 = (float) Math.pow(1 - 1f / (1f + MathHelper.lerp(tickProgress, prevSquish, squish)), 2);
        matrices.scale(1, 1 - squish2, 1);
        matrices.translate(0.5, 0, 0.5);
        matrices.scale(1 + squish2 / 2, 1, 1 + squish2 / 2);
        matrices.translate(-0.5, 0, -0.5);
        BlockState state = entity.getCachedState();
        List<BlockModelPart> list = this.renderManager.getModel(state).getParts(Random.create(state.getRenderingSeed(entity.getPos())));
        this.renderManager.getModelRenderer().render(entity.getWorld(), list, state, entity.getPos(), matrices, vertexConsumers.getBuffer(RenderLayers.getMovingBlockLayer(state)), false, overlay);
        matrices.pop();
    }
}
