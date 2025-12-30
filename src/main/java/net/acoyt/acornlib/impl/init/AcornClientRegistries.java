package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.armPose.CustomArmPose;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

@Environment(EnvType.CLIENT)
public interface AcornClientRegistries {
    RegistryKey<Registry<CustomArmPose>> armPoseKey = RegistryKey.ofRegistry(AcornLib.id("custom_arm_pose"));
    Registry<CustomArmPose> CUSTOM_ARM_POSE = FabricRegistryBuilder.createSimple(armPoseKey)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {
        // Client Registries are Registered Statically
    }
}
