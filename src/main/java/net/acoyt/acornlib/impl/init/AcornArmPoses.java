package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.armPose.CustomArmPose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.registry.Registry;

@Environment(EnvType.CLIENT)
public interface AcornArmPoses {
    CustomArmPose TEST = create("test", new CustomArmPose(
            (leftArm, ctx) -> {
                leftArm.pitch = Math.min(ctx.headPitch() + 80.0f, 80.77f);
            },
            (rightArm, ctx) -> {
                rightArm.pitch = Math.min(ctx.headPitch() + 80.0f, 80.77f);
            },
            false
    ));

    private static <T extends CustomArmPose> T create(String name, T pose) {
        return Registry.register(AcornClientRegistries.CUSTOM_ARM_POSE, AcornLib.id(name), pose);
    }

    static void init() {
        // Custom Arm Poses are Registered Statically
    }
}
