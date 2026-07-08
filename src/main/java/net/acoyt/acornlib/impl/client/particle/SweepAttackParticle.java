package net.acoyt.acornlib.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

/**
 * @author AcoYT
 */
//? if > 1.21.8 {
/*public class SweepAttackParticle extends SingleQuadParticle {
 *///? } else {
public class SweepAttackParticle extends TextureSheetParticle {
//? }
    private final SpriteSet spriteWithAge;

    private SweepAttackParticle(ClientLevel level, double x, double y, double z, double scale, SpriteSet spriteWithAge) {
        super(level,
                x, y, z,
                0.0F, 0.0F, 0.0F
                //? if > 1.21.8 {
                /*, spriteWithAge.first()
                *///? }
        );
        this.spriteWithAge = spriteWithAge;
        this.lifetime = 4;
        this.quadSize = 1.0F - (float)scale * 0.5F;
        this.setSpriteFromAge(spriteWithAge);
    }

    public int getLightCoords(float a) {
        return 0xFFFFFF;
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

    //? if > 1.21.5 {
    /*public Layer getLayer() {
        return Layer.TRANSLUCENT;
    }
    *///? } else {
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    //? }

    @Environment(EnvType.CLIENT)
    public record Provider(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {
        //? if > 1.21.8 {
        /*public Particle createParticle(SimpleParticleType parameters, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ, RandomSource random) {
            return new SweepAttackParticle(level, x, y, z, velocityX, this.spriteSet);
        }
        *///? } else {
        public Particle createParticle(SimpleParticleType parameters, ClientLevel level, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SweepAttackParticle(level, x, y, z, velocityX, this.spriteSet);
        }
        //? }
    }
}
