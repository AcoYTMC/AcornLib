package net.acoyt.acornlib.api.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

/// Because Myth was really vague
@SuppressWarnings("unused")
public class VelocityUtils {
    /**
     * @param living The entity to apply the velocity to
     * @param multiplier the multiplier
     * @param inverted whether the multiplier should be inverted or not
     */
    public static void applyVelocityInLookDirection(LivingEntity living, float multiplier, boolean inverted) {
        living.setDeltaMovement(
                living.getLookAngle().x * (inverted ? -multiplier : multiplier),
                living.getLookAngle().y * (inverted ? -multiplier : multiplier),
                living.getLookAngle().z * (inverted ? -multiplier : multiplier)
        );
        living.hurtMarked = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param xMulti The X multiplier
     * @param yMulti The Y multiplier
     * @param zMulti The Z multiplier
     * @param inverted whether the multiplier should be inverted or not
     */
    public static void applyVelocityInLookDirection(LivingEntity living, float xMulti, float yMulti, float zMulti, boolean inverted) {
        living.setDeltaMovement(
                living.getLookAngle().x * (inverted ? -xMulti : xMulti),
                living.getLookAngle().y * (inverted ? -yMulti : yMulti),
                living.getLookAngle().z * (inverted ? -zMulti : zMulti)
        );
        living.hurtMarked = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param velocity the Vec3d velocity to apply
     */
    public static void applyExactVelocity(LivingEntity living, Vec3 velocity, boolean inverted) {
        living.setDeltaMovement(velocity);
        living.hurtMarked = true;
    }

    /**
     * @param living The entity to apply the velocity to
     * @param x the x velocity to apply
     * @param y the y velocity to apply
     * @param z the z velocity to apply
     */
    public static void applyExactVelocity(LivingEntity living, double x, double y, double z, boolean inverted) {
        living.setDeltaMovement(
                inverted ? -x : x,
                inverted ? -y : y,
                inverted ? -z : z
        );
        living.hurtMarked = true;
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
        target.setDeltaMovement(byEntity.position().subtract(target.position()).scale(inverted ? -multiplier : multiplier));
        target.hurtMarked = true;
    }

    public static void applyVelocityByPos(LivingEntity target, Vec3 pos, float multiplier, boolean inverted) {
        target.setDeltaMovement(target.position().subtract(pos).scale(inverted ? -multiplier : multiplier));
        target.hurtMarked = true;
    }
}
