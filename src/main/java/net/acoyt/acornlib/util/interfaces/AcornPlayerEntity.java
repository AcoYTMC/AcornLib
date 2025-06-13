package net.acoyt.acornlib.util.interfaces;

public interface AcornPlayerEntity {
    default boolean acornlib$holdingAttack() {
        return false;
    }

    default void acornlib$setHoldingAttack(boolean attackHeld) {}

    default int acornlib$getHoldingAttackTime() {
        return 0;
    }

    default boolean acornlib$holdingUse() {
        return false;
    }

    default void acornlib$setHoldingUse(boolean useHeld) {}

    default int acornlib$getHoldingUseTime() {
        return 0;
    }
}
