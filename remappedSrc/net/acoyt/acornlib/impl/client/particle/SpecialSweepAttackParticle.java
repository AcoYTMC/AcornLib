package net.acoyt.acornlib.impl.client.particle;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.ARGB;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SpecialSweepAttackParticle extends TextureSheetParticle {
    private final SpriteSet spriteWithAge;
    private final Vector3f baseColor;
    private final Vector3f shadowColor;

    private SpecialSweepAttackParticle(ClientLevel world, double x, double y, double z, SpriteSet spriteWithAge, SweepParticleEffect particleEffect) {
        super(world, x, y, z, 0.0D, 0.0D, 0.0D);
        this.spriteWithAge = spriteWithAge;
        this.lifetime = 4;
        this.quadSize = 1.0F;
        this.setSpriteFromAge(spriteWithAge);
        this.baseColor = ARGB.vector3fFromRGB24(particleEffect.baseColor());
        this.shadowColor = ARGB.vector3fFromRGB24(particleEffect.shadowColor());
    }

    @Override
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

    @Override
    protected void renderRotatedQuad(VertexConsumer vertexConsumer, Camera camera, Quaternionf quaternionf, float tickProgress) {
        this.setSprite(this.spriteWithAge.get(this.age, this.lifetime * 2));
        this.setColor(this.baseColor.x, this.baseColor.y, this.baseColor.z);
        super.renderRotatedQuad(vertexConsumer, camera, quaternionf, tickProgress);
        this.setSprite(this.spriteWithAge.get(this.age + this.lifetime, this.lifetime * 2));
        this.setColor(this.shadowColor.x, this.shadowColor.y, this.shadowColor.z);
        super.renderRotatedQuad(vertexConsumer, camera, quaternionf, tickProgress);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleProvider<SweepParticleEffect> {
        private final SpriteSet spriteProvider;

        public Factory(SpriteSet spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SweepParticleEffect parameters, ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new SpecialSweepAttackParticle(world, x, y, z, spriteProvider, parameters);
        }
    }
}
