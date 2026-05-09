package net.acoyt.acornlib.impl.util.interfaces;

import net.minecraft.util.Identifier;

/**
 * @author AcoYT
 */
public interface IdContained {
    String getModId();
    default Identifier id(String path) {
        return Identifier.of(this.getModId(), path);
    }
}
