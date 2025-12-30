package net.acoyt.acornlib.impl.client.particle;

import net.acoyt.acornlib.api.util.PortingUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SpecialSweepAttackParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteWithAge;
    private final Vector3f baseColor;
    private final Vector3f shadowColor;

    private SpecialSweepAttackParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteWithAge, SweepParticleEffect particleEffect) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.spriteWithAge = spriteWithAge;
        this.maxAge = 4;
        this.scale = 1.0F;
        this.setSpriteForAge(spriteWithAge);
        this.baseColor = PortingUtils.toVector(particleEffect.baseColor());
        this.shadowColor = PortingUtils.toVector(particleEffect.shadowColor());
    }

    @Override
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

    @Override
    protected void method_60373(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float tickProgress) {
        this.setSprite(this.spriteWithAge.getSprite(this.age, this.maxAge * 2));
        this.setColor(this.baseColor.x, this.baseColor.y, this.baseColor.z);
        super.method_60373(vertexConsumer, camera, quaternionf, tickProgress);
        this.setSprite(this.spriteWithAge.getSprite(this.age + this.maxAge, this.maxAge * 2));
        this.setColor(this.shadowColor.x, this.shadowColor.y, this.shadowColor.z);
        super.method_60373(vertexConsumer, camera, quaternionf, tickProgress);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SweepParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SweepParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SpecialSweepAttackParticle(world, x, y, z, spriteProvider, parameters);
        }
    }
}
