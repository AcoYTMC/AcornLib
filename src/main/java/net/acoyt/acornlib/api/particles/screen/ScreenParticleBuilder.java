package net.acoyt.acornlib.api.particles.screen;

import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.Identifier;

import java.util.function.BiConsumer;

//? if > 1.21.11 {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
*///? } else if <= 1.21.8 {
/*import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import org.joml.Matrix4f;
*///? }

//? if > 1.21.5 {
import net.minecraft.client.renderer.RenderPipelines;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import org.joml.Matrix3x2fStack;

//? }

/**
 * @author AcoYT
 */
public class ScreenParticleBuilder {
    private int x;
    private int y;
    //? if > 1.21.5 {
    private RenderPipeline pipeline = RenderPipelines.GUI_TEXTURED;
     //? }
    private Identifier texture;
    private float u = 0.0F;
    private float v = 0.0F;
    private int width;
    private int height;
    private int textureWidth;
    private int textureHeight;
    private int color = 0xFFFFFFFF;
    //? if > 1.21.5 {
    private BiConsumer<Matrix3x2fStack, Float> rotationConsumer = (stack, tickDelta) -> {};
     //? } else {
    /*private BiConsumer<PoseStack, Float> rotationConsumer = (stack, tickDelta) -> {};
    *///? }

    private ScreenParticleBuilder() {}

    public static ScreenParticleBuilder builder() {
        return new ScreenParticleBuilder();
    }

    public ScreenParticleBuilder position(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    //? if > 1.21.5 {
    public ScreenParticleBuilder pipeline(RenderPipeline pipeline) {
        this.pipeline = pipeline;
        return this;
    }
    //? }

    public ScreenParticleBuilder texture(Identifier texture) {
        this.texture = texture;
        return this;
    }

    public ScreenParticleBuilder texture(Identifier texture, float u, float v) {
        this.texture = texture;
        this.u = u;
        this.v = v;
        return this;
    }

    public ScreenParticleBuilder size(int width, int height) {
        this.width = width;
        this.height = height;
        this.textureWidth = width;
        this.textureHeight = height;
        return this;
    }

    public ScreenParticleBuilder size(int width, int height, int textureWidth, int textureHeight) {
        this.width = width;
        this.height = height;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return this;
    }

    public ScreenParticleBuilder color(int color) {
        this.color = color;
        return this;
    }

    //? if > 1.21.5 {
    public ScreenParticleBuilder rotation(BiConsumer<Matrix3x2fStack, Float> rotation) {
        this.rotationConsumer = rotation;
        return this;
    }
    //? } else {
    /*public ScreenParticleBuilder rotation(BiConsumer<PoseStack, Float> rotation) {
        this.rotationConsumer = rotation;
        return this;
    }
    *///? }

    //? if > 1.21.5 {
    //? if > 1.21.11 {
    /*public void draw(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
    *///? } else {
    public void draw(GuiGraphics graphics, DeltaTracker deltaTracker) {
    //? }
        graphics.pose().pushMatrix();

        this.rotationConsumer.accept(graphics.pose(), deltaTracker.getGameTimeDeltaPartialTick(false));
        graphics.blit(
                this.pipeline,
                this.texture,
                this.x, this.y,
                this.u, this.v,
                this.width, this.height,
                this.textureWidth, this.textureHeight,
                this.color
        );

        graphics.pose().popMatrix();
    }
    //? } else {
    /*public void draw(GuiGraphics graphics, DeltaTracker deltaTracker) {
        graphics.pose().pushPose();

        this.rotationConsumer.accept(graphics.pose(), deltaTracker.getGameTimeDeltaPartialTick(false));

        int x2 = this.x + this.width;
        int y2 = this.y + this.height;
        float u1 = (this.u + 0.0F) / this.textureWidth;
        float u2 = (this.u + this.width) / this.textureWidth;
        float v1 = (this.v + 0.0F) / this.textureHeight;
        float v2 = (this.v + this.height) / this.textureHeight;

        RenderSystem.setShaderTexture(0, this.texture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        Matrix4f matrix4f = graphics.pose().last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.addVertex(matrix4f, x, y, 0).setUv(u1, v1).setColor(this.color);
        bufferBuilder.addVertex(matrix4f, x, y2, 0).setUv(u1, v2).setColor(this.color);
        bufferBuilder.addVertex(matrix4f, x2, y2, 0).setUv(u2, v2).setColor(this.color);
        bufferBuilder.addVertex(matrix4f, x2, y, 0).setUv(u2, v1).setColor(this.color);
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow());

        graphics.pose().popPose();
    }
    *///? }
}
