package net.acoyt.acornlib.impl.index.client;

//? if > 1.21.4 {
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
//? }

//? if > 1.21.11 {
/*import com.mojang.blaze3d.pipeline.ColorTargetState;
import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.platform.CompareOp;
*///? }

/**
 * @author AcoYT
 */
public interface AcornRenderPipelines {
    //? if > 1.21.11 {
    /*RenderPipeline ADDITIVE_PARTICLE = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
            .withLocation("pipeline/eyes")
            .withVertexShader("core/entity")
            .withFragmentShader("core/entity")
            .withShaderDefine("EMISSIVE")
            .withShaderDefine("NO_OVERLAY")
            .withShaderDefine("NO_CARDINAL_LIGHTING")
            .withSampler("Sampler0")
            .withColorTargetState(new ColorTargetState(BlendFunction.ADDITIVE))
            .withVertexFormat(DefaultVertexFormat.PARTICLE, VertexFormat.Mode.QUADS)
            .withDepthStencilState(new DepthStencilState(CompareOp.LESS_THAN_OR_EQUAL, false))
            .build();
    *///? } else if > 1.21.4 {
    RenderPipeline ADDITIVE_PARTICLE = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
            .withLocation("pipeline/eyes")
            .withVertexShader("core/entity")
            .withFragmentShader("core/entity")
            .withShaderDefine("EMISSIVE")
            .withShaderDefine("NO_OVERLAY")
            .withShaderDefine("NO_CARDINAL_LIGHTING")
            .withSampler("Sampler0")
            .withBlend(BlendFunction.TRANSLUCENT)
            .withDepthWrite(false)
            .withVertexFormat(DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS)
            .build();
    //? }
}
