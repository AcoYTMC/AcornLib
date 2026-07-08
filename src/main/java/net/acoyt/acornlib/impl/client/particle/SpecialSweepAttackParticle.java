package net.acoyt.acornlib.impl.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import org.joml.Quaternionf;
import org.joml.Vector3f;

//? if > 1.21.11 {
/*import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
 *///? } else if > 1.21.5 {
import net.minecraft.client.renderer.state.QuadParticleRenderState;
 //? } else {
//? }

//? if > 1.21.5 {
import net.minecraft.client.Camera;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;
//? }

//? if > 1.21.1 {
import net.minecraft.util.ARGB;
 //? } else {
/*import net.minecraft.util.FastColor.ARGB32;
        *///? }

/**
 * @author AcoYT
 */
//? if > 1.21.8 {
public class SpecialSweepAttackParticle extends SingleQuadParticle {
 //? } else {
/*public class SpecialSweepAttackParticle extends TextureSheetParticle {
*///? }
    private final SpriteSet spriteWithAge;
    private final Vector3f baseColor;
    private final Vector3f shadowColor;

    private SpecialSweepAttackParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteWithAge, SweepParticleEffect particleEffect) {
        super(level,
                x, y, z,
                0.0D, 0.0D, 0.0D
                //? if > 1.21.8 {
                , spriteWithAge.first()
                 //? }
        );
        this.spriteWithAge = spriteWithAge;
        this.lifetime = 4;
        this.quadSize = 1.0F;
        this.setSpriteFromAge(spriteWithAge);
        //? if > 1.21.1 {
        this.baseColor = ARGB.vector3fFromRGB24(particleEffect.baseColor());
        this.shadowColor = ARGB.vector3fFromRGB24(particleEffect.shadowColor());
        //? } else {
        /*this.baseColor = new Vector3f(
                ARGB32.red(particleEffect.baseColor()) / 255.0F,
                ARGB32.green(particleEffect.baseColor()) / 255.0F,
                ARGB32.blue(particleEffect.baseColor()) / 255.0F
        );

        this.shadowColor = new Vector3f(
                ARGB32.red(particleEffect.shadowColor()) / 255.0F,
                ARGB32.green(particleEffect.shadowColor()) / 255.0F,
                ARGB32.blue(particleEffect.shadowColor()) / 255.0F
        );
        *///? }
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.spriteWithAge);
        }
    }

    //? if > 1.21.11 {
    /*public void extractRotatedQuad(QuadParticleRenderState submittable, Camera camera, Quaternionf rotation, float tickProgress) {
        this.setSprite(this.spriteWithAge.get(this.age, this.lifetime * 2));
        this.setColor(this.baseColor.x, this.baseColor.y, this.baseColor.z);
        super.extractRotatedQuad(submittable, camera, rotation, tickProgress);

        this.setSprite(this.spriteWithAge.get(this.age + this.lifetime, this.lifetime * 2));
        this.setColor(this.shadowColor.x, this.shadowColor.y, this.shadowColor.z);
        super.extractRotatedQuad(submittable, camera, rotation, tickProgress);
    }

    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    *///? } else if > 1.21.5 {
    public void extractRotatedQuad(QuadParticleRenderState renderState, Camera camera, Quaternionf rotation, float tickProgress) {
        this.setSprite(this.spriteWithAge.get(this.age, this.lifetime * 2));
        this.setColor(this.baseColor.x, this.baseColor.y, this.baseColor.z);
        super.extractRotatedQuad(renderState, camera, rotation, tickProgress);

        this.setSprite(this.spriteWithAge.get(this.age + this.lifetime, this.lifetime * 2));
        this.setColor(this.shadowColor.x, this.shadowColor.y, this.shadowColor.z);
        super.extractRotatedQuad(renderState, camera, rotation, tickProgress);
    }

    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    //? } else {
    /*public void renderRotatedQuad(VertexConsumer vertexConsumer, Camera camera, Quaternionf rotation, float tickProgress) {
        this.setSprite(this.spriteWithAge.get(this.age, this.lifetime * 2));
        this.setColor(this.baseColor.x, this.baseColor.y, this.baseColor.z);
        super.renderRotatedQuad(vertexConsumer, camera, rotation, tickProgress);

        this.setSprite(this.spriteWithAge.get(this.age + this.lifetime, this.lifetime * 2));
        this.setColor(this.shadowColor.x, this.shadowColor.y, this.shadowColor.z);
        super.renderRotatedQuad(vertexConsumer, camera, rotation, tickProgress);
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    *///? }

    @Environment(EnvType.CLIENT)
    public record Provider(SpriteSet spriteProvider) implements ParticleProvider<SweepParticleEffect> {
        //? if > 1.21.8 {
        public Particle createParticle(SweepParticleEffect parameters, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new SpecialSweepAttackParticle(level, x, y, z, this.spriteProvider, parameters);
        }
        //? } else {
        /*public Particle createParticle(SweepParticleEffect parameters, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SpecialSweepAttackParticle(level, x, y, z, this.spriteProvider, parameters);
        }
        *///? }
    }
}
