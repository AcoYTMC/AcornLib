package net.acoyt.acornlib.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class SweepAttackParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteWithAge;

    private SweepAttackParticle(ClientWorld world, double x, double y, double z, double scale, SpriteProvider spriteWithAge) {
        super(world, x, y, z, 0.0F, 0.0F, 0.0F);
        this.spriteWithAge = spriteWithAge;
        this.maxAge = 4;
        this.scale = 1.0F - (float)scale * 0.5F;
        this.setSpriteForAge(spriteWithAge);
    }

    protected int getBrightness(float tint) {
        return 15728880;
    }

    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge) {
            this.markDead();
        } else {
            this.setSpriteForAge(this.spriteWithAge);
        }

    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider spriteSet) implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SweepAttackParticle(world, x, y, z, velocityX, this.spriteSet);
        }
    }
}
