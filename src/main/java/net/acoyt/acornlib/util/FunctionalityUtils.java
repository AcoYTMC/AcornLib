package net.acoyt.acornlib.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

/// Because Myth was really vague
public class FunctionalityUtils {
    public static void applyVelocityInLookDirection(LivingEntity living, float multiplier, boolean inverted) {
        living.setVelocity(
                living.getRotationVector().x * (inverted ? -multiplier : multiplier),
                living.getRotationVector().y * (inverted ? -multiplier : multiplier),
                living.getRotationVector().z * (inverted ? -multiplier : multiplier)
        );
        living.velocityModified = true;
    }

    public static void applyExactVelocity(LivingEntity living, Vec3d velocity) {
        living.setVelocity(velocity);
        living.velocityModified = true;
    }

    public static void applyExactVelocity(LivingEntity living, double x, double y, double z) {
        living.setVelocity(x, y, z);
        living.velocityModified = true;
    }
}
