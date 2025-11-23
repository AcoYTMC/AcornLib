package net.acoyt.acornlib.impl.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class SweepAttackParticle extends TextureSheetParticle {
    private final SpriteSet spriteWithAge;

    private SweepAttackParticle(ClientLevel world, double x, double y, double z, double scale, SpriteSet spriteWithAge) {
        super(world, x, y, z, 0.0F, 0.0F, 0.0F);
        this.spriteWithAge = spriteWithAge;
        this.lifetime = 4;
        this.quadSize = 1.0F - (float)scale * 0.5F;
        this.setSpriteFromAge(spriteWithAge);
    }

    protected int getLightColor(float tint) {
        return 15728880;
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

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteSet spriteSet) implements ParticleProvider<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SweepAttackParticle(world, x, y, z, velocityX, this.spriteSet);
        }
    }
}
