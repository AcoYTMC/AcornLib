package net.acoyt.acornlib.api.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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
}
