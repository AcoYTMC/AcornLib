package net.acoyt.acornlib.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

/// Because Myth was really vague
@SuppressWarnings("unused")
public class VelocityUtils {
    /**
     * @param living The entity to apply the velocity to
     * @param multiplier the multiplier
     * @param inverted whether the multiplier should be inverted or not
     */
    public static void applyVelocityInLookDirection(LivingEntity living, float multiplier, boolean inverted) {
        living.setVelocity(
                living.getRotationVector().x * (inverted ? -multiplier : multiplier),
                living.getRotationVector().y * (inverted ? -multiplier : multiplier),
                living.getRotationVector().z * (inverted ? -multiplier : multiplier)
        );
        living.velocityModified = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param xMulti The X multiplier
     * @param yMulti The Y multiplier
     * @param zMulti The Z multiplier
     * @param inverted whether the multiplier should be inverted or not
     */
    public static void applyVelocityInLookDirection(LivingEntity living, float xMulti, float yMulti, float zMulti, boolean inverted) {
        living.setVelocity(
                living.getRotationVector().x * (inverted ? -xMulti : xMulti),
                living.getRotationVector().y * (inverted ? -yMulti : yMulti),
                living.getRotationVector().z * (inverted ? -zMulti : zMulti)
        );
        living.velocityModified = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param velocity the Vec3d velocity to apply
     */
    public static void applyExactVelocity(LivingEntity living, Vec3d velocity) {
        living.setVelocity(velocity);
        living.velocityModified = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param x the x velocity to apply
     * @param y the y velocity to apply
     * @param z the z velocity to apply
     */
    public static void applyExactVelocity(LivingEntity living, double x, double y, double z) {
        living.setVelocity(x, y, z);
        living.velocityModified = true;
    }
}
