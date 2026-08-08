package net.acoyt.acornlib.api.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * @author AcoYT
 */
@SuppressWarnings("unused")
public class ParticleUtils {
    public static void spawnSweepParticles(ParticleOptions particle, Player player) {
        double deltaX = -Mth.sin((float) (player.getYRot() * (Math.PI / 180.0F)));
        double deltaZ = Mth.cos((float) (player.getYRot() * (Math.PI / 180.0F)));
        Level var7 = player.level();
        if (var7 instanceof ServerLevel serverWorld) {
            serverWorld.sendParticles(
                    particle,
                    player.getX() + deltaX,
                    player.getY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    public static void spawnSweepParticles(ParticleOptions particle, int count, Player player) {
        double deltaX = -Mth.sin((float) (player.getYRot() * (Math.PI / 180.0F)));
        double deltaZ = Mth.cos((float) (player.getYRot() * (Math.PI / 180.0F)));
        Level var7 = player.level();
        if (var7 instanceof ServerLevel serverWorld) {
            serverWorld.sendParticles(
                    particle,
                    player.getX() + deltaX,
                    player.getY(0.5F),
                    player.getZ() + deltaZ,
                    count, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }

    public static void spawnParticleRing(ParticleOptions options, Level level, Vec3 pos, double radius, int count) {
        for (int i = 0; i < count; i++) {
            double angle = Math.PI * 2 * i / count;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;

            if (options.getType() == ParticleTypes.BLOCK) {
                BlockPos blockPos = new BlockPos.MutableBlockPos().set(pos.x + x, pos.y, pos.z + z).immutable();
                options = new BlockParticleOption(ParticleTypes.BLOCK, level.getBlockState(blockPos));
            }

            if (level.isClientSide()) {
                level.addParticle(
                        options,
                        pos.x + x, pos.y, pos.z + z,
                        0.0, 0.0, 0.0
                );
            } else {
                ((ServerLevel)level).sendParticles(
                        options,
                        pos.x + x, pos.y, pos.z + z,
                        1,
                        0.0, 0.0, 0.0,
                        0.1
                );
            }
        }
    }

    public static void spawnDirectionalParticleRing(ParticleOptions options, Level level, Vec3 cameraPos, float xRot, float yRot, float distance, float radius, int count) {
        Matrix4f mat = new Matrix4f().rotateYXZ((float) Math.PI - yRot, -xRot, 0);

        for (int i = 0; i < count; i++) {
            double angle = Math.PI * 2 * i / count;
            float x = (float) Math.cos(angle) * radius;
            float y = (float) Math.sin(angle) * radius;

            Vector4f transformed = mat.transform(new Vector4f(x, y, distance, 1));
            Vector3f actualPos = new Vec3(transformed.x + cameraPos.x, transformed.y + cameraPos.y, transformed.z + cameraPos.z).toVector3f();

            if (level.isClientSide()) {
                level.addParticle(
                        options,
                        actualPos.x, actualPos.y, actualPos.z,
                        0.0, 0.0, 0.0
                );
            } else {
                ((ServerLevel)level).sendParticles(
                        options,
                        actualPos.x, actualPos.y, actualPos.z,
                        1,
                        0.0, 0.0, 0.0,
                        0.1
                );
            }
        }
    }
}
