package net.acoyt.acornlib.api.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import org.jetbrains.annotations.ApiStatus;

//? if > 1.21.11 {
/*import net.minecraft.client.renderer.state.level.QuadParticleRenderState;
import org.joml.Quaternionf;
import net.acoyt.acornlib.impl.AcornLibClient;
*///? } else if > 1.21.8 {
import net.minecraft.client.renderer.state.QuadParticleRenderState;
//? }

//? if > 1.21.8 {
import net.minecraft.util.RandomSource;
//? }

//? if > 1.21.1 {
import net.minecraft.util.ARGB;
//? } else {
/*import net.minecraft.util.FastColor.ARGB32;
 *///? }

/**
 * @author AcoYT
 */
@ApiStatus.Experimental
//? if > 1.21.8 {
public class SpecialParticle extends SingleQuadParticle {
//? } else {
/*public class SpecialParticle extends TextureSheetParticle {
*///? }
    private final SpecialParticleData particleData;

    public SpecialParticle(ClientLevel level, SpriteSet spriteSet, SpecialParticleData particleData) {
        super(level,
                particleData.position().x, particleData.position().y, particleData.position().z,
                particleData.velocity().x, particleData.velocity().y, particleData.velocity().z
                //? if > 1.21.8 {
                , spriteSet.first()
                //? }
        );

        this.particleData = particleData;
    }

    public void tick() {
        super.tick();
        //if (this.particleData.particle() instanceof Particle particle) {
        //    particle.tick();
        //}
    }

    //? if > 1.21.11 {
    /*public void extractRotatedQuad(QuadParticleRenderState renderState, Quaternionf rotation, float x, float y, float z, float partialTickTime) {
        rotation.add(this.particleData.rotation());
        if (this.particleData.size() != - 1.0F) this.scale(this.particleData.size());
        this.setColor(ARGB.redFloat(particleData.color()), ARGB.greenFloat(particleData.color()), ARGB.blueFloat(particleData.color()));
        super.extractRotatedQuad(renderState, rotation, x, y, z, partialTickTime);
    }

    public ParticleRenderType getGroup() {
        return AcornLibClient.SPECIAL;
    }
*///? }

    //? if > 1.21.8 {
    public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    //? } else {
    /*public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    *///? }

    @Environment(EnvType.CLIENT)
    public record Provider(SpriteSet spriteProvider) implements ParticleProvider<SpecialParticleData> {
        //? if > 1.21.8 {
        public Particle createParticle(SpecialParticleData particleData, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new SpecialParticle(level, this.spriteProvider, particleData);
        }
        //? } else {
        /*public Particle createParticle(SpecialParticleData particleData, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SpecialParticle(level, this.spriteProvider, particleData);
        }
        *///? }
    }
}
