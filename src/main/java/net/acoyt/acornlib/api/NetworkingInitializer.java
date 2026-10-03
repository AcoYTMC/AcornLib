package net.acoyt.acornlib.api;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * @author AcoYT
 */
public interface NetworkingInitializer {
    void registerTypes();
    void registerServerboundPackets();
    @Environment(EnvType.CLIENT)
    void registerClientboundPackets();
}
