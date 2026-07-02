package net.acoyt.acornlib.impl;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class AcornLib implements ModInitializer {
    public static final String MOD_ID = /*$ mod_id*/ "acornlib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final String VERSION = /*$ mod_version*/ "multi-r1";
    public static final String MINECRAFT = /*$ minecraft*/ "1.21.1";

    public void onInitialize() {
        //
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
