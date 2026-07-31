package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.resources.Identifier;

/**
 * @author AcoYT
 */
public interface IdContained {
    String getModId();
    default Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(this.getModId(), path);
    }
}
