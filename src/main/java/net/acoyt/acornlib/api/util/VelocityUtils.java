package net.acoyt.acornlib.api.util;

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
    public static void applyExactVelocity(LivingEntity living, Vec3d velocity, boolean inverted) {
        living.setVelocity(velocity);
        living.velocityModified = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param x the x velocity to apply
     * @param y the y velocity to apply
     * @param z the z velocity to apply
     */
    public static void applyExactVelocity(LivingEntity living, double x, double y, double z, boolean inverted) {
        living.setVelocity(
                inverted ? -x : x,
                inverted ? -y : y,
                inverted ? -z : z
        );
        living.velocityModified = true;
    }

    /**
     * Sets the velocity of the target to the position of the byEntity minus the target's position times the multiplier
     * target.setVelocity(byEntity.getPos().subtract(target.getPos()).multiply(multiplier));
     * @param target The entity to apply the velocity to
     * @param byEntity the entity to "measure" this multiplier by
     * @param multiplier the multiplier
     * @param inverted whether the multiplier should be inverted or not
     */
    public static void applyVelocityByEntity(LivingEntity target, LivingEntity byEntity, float multiplier, boolean inverted) {
        target.setVelocity(byEntity.getPos().subtract(target.getPos()).multiply(inverted ? -multiplier : multiplier));
        target.velocityModified = true;
    }
}
