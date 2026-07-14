package net.acoyt.acornlib.impl.client.block.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

//? if > 1.21.11 {
/*import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
*///? } else if > 1.21.4 {
/*import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlas;
*///? } else {
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
//? }

//? if > 1.21.8 {
/*import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.client.block.state.PlushBlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
*///? }

/**
 * @author AcoYT
 */
@Environment(EnvType.CLIENT)
//? if > 1.21.8 {
/*public class PlushBlockEntityRenderer implements BlockEntityRenderer<PlushBlockEntity, PlushBlockEntityRenderState> {
 *///? } else {
public class PlushBlockEntityRenderer implements BlockEntityRenderer<PlushBlockEntity> {
    //? }
    //? if > 1.21.11 {
    /*private final BlockModelResolver modelResolver;
     *///? } else {
    private final BlockRenderDispatcher dispatcher;
    //? }

    public PlushBlockEntityRenderer(BlockEntityRendererProvider.@NotNull Context ctx) {
        //? if > 1.21.11 {
        /*this.modelResolver = ctx.blockModelResolver();
         *///? } else {
        this.dispatcher = Minecraft.getInstance().getBlockRenderer();
        //? }
    }

    //? if > 1.21.8 {
    /*public PlushBlockEntityRenderState createRenderState() {
        return new PlushBlockEntityRenderState();
    }
    *///? }

    //? if > 1.21.11 {
    /*public void submit(PlushBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        Minecraft minecraft = Minecraft.getInstance();
        float tickDelta = MiscUtils.getTickDelta();

        poseStack.pushPose();
        var squish = state.squish;
        var lastSquish = squish * 3;
        var newSquish = (float) Math.pow(1 - 1f / (1f + Mth.lerp(tickDelta, lastSquish, squish)), 2);
        poseStack.scale(1.0F, 1.0F - newSquish, 1.0F);

        BlockModelRenderState renderState = new BlockModelRenderState();
        this.modelResolver.update(renderState, state.plushState, BlockDisplayContext.create());
        renderState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

        poseStack.popPose();
    }
    *///? } else if > 1.21.4 {
    /*public void submit(PlushBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        float tickDelta = MiscUtils.getTickDelta();

        poseStack.pushPose();
        var squish = state.squish;
        var lastSquish = squish * 3;
        var newSquish = (float) Math.pow(1 - 1.0F / (1.0F + Mth.lerp(tickDelta, lastSquish, squish)), 2);
        poseStack.scale(1.0F, 1.0F - newSquish, 1.0F);

        BlockStateModel stateModel = dispatcher.getBlockModel(state.blockState);

        collector.submitBlockModel(
                poseStack,
                RenderTypes.entityCutoutNoCull(TextureAtlas.LOCATION_BLOCKS),
                stateModel,
                1.0F, 1.0F, 1.0F,
                state.lightCoords,
                OverlayTexture.NO_OVERLAY,
                0
        );

        poseStack.popPose();
    }
    *///? } else {
    public void render(PlushBlockEntity entity, float tickDelta, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay) {
        poseStack.pushPose();

        var squish = entity.getSquish();
        var lastSquish = squish * 3;
        var newSquish = (float) Math.pow(1 - 1.0F / (1.0F + Mth.lerp(tickDelta, lastSquish, squish)), 2);

        poseStack.scale(1.0F, 1.0F - newSquish, 1.0F);

        dispatcher.getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(ItemBlockRenderTypes.getRenderType(entity.getBlockState(), false)),
                entity.getBlockState(),
                this.dispatcher.getBlockModel(entity.getBlockState()),
                1.0F, 1.0F, 1.0F,
                light,
                overlay
        );

        poseStack.popPose();
    }
    //? }

    //? if > 1.21.8 {
    /*public void extractRenderState(PlushBlockEntity blockEntity, PlushBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.squish = blockEntity.getSquish();
        state.plushState = blockEntity.getBlockState();
    }
    *///? }
}
