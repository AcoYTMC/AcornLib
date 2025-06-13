package net.acoyt.acornlib.client.render;

import net.acoyt.acornlib.plush.ThrownPlushEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class ThrownPlushEntityRenderer extends EntityRenderer<ThrownPlushEntity, ThrownPlushEntityRenderState> {
    public final ItemRenderer itemRenderer;
    private ThrownPlushEntity thrownPlush;
    private ItemStack stack;

    public ThrownPlushEntityRenderer(EntityRendererFactory.Context context, ItemStack stack) {
        super(context);
        this.itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        this.shadowRadius = 0.5F;
        this.shadowOpacity = 0.5F;
        this.stack = stack;
    }

    public void render(ThrownPlushEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        float tickDelta = MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false);
        ItemStack stack = this.stack;
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, thrownPlush.lastYaw, thrownPlush.getYaw()) + 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, thrownPlush.lastPitch, thrownPlush.getPitch()) - 90.0F));
        itemRenderer.renderItem(stack, ItemDisplayContext.HEAD, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, thrownPlush.getWorld(), 0);
        matrices.scale(1.1f, 1.1f, 1.1f);
        matrices.translate(0.1F, -0.2F, 0.0F);

        matrices.pop();

        super.render(state, matrices, vertexConsumers, light);
    }

    public ThrownPlushEntityRenderState createRenderState() {
        return new ThrownPlushEntityRenderState();
    }

    @Override
    public void updateRenderState(ThrownPlushEntity entity, ThrownPlushEntityRenderState state, float tickProgress) {
        this.thrownPlush = entity;
        super.updateRenderState(entity, state, tickProgress);
    }
}
