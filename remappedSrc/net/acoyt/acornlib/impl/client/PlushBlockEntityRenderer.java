package net.acoyt.acornlib.impl.client;

import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.List;

@Environment(EnvType.CLIENT)
public class PlushBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final BlockRenderDispatcher renderManager;

    public PlushBlockEntityRenderer(BlockEntityRendererProvider.@NotNull Context ctx) {
        this.renderManager = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(T entity, float tickProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay, Vec3 cameraPos) {
        matrices.pushPose();
        double squish = entity instanceof PlushBlockEntity plush ? plush.squish : 0;
        double prevSquish = squish * 3;
        float squish2 = (float) Math.pow(1 - 1f / (1f + Mth.lerp(tickProgress, prevSquish, squish)), 2);
        matrices.scale(1, 1 - squish2, 1);
        matrices.translate(0.5, 0, 0.5);
        matrices.scale(1 + squish2 / 2, 1, 1 + squish2 / 2);
        matrices.translate(-0.5, 0, -0.5);
        BlockState state = entity.getBlockState();
        List<BlockModelPart> list = this.renderManager.getBlockModel(state).collectParts(RandomSource.create(state.getSeed(entity.getBlockPos())));
        this.renderManager.getModelRenderer().tesselateBlock(entity.getLevel(), list, state, entity.getBlockPos(), matrices, vertexConsumers.getBuffer(ItemBlockRenderTypes.getMovingBlockRenderType(state)), false, overlay);
        matrices.popPose();
    }
}
