package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.resources.ResourceLocation;

/**
 * @author AcoYT
 */
public interface IdContained {
    String getModId();
    default ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(this.getModId(), path);
    }
}
