package net.acoyt.acornlib.api.template;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AcoYT
 */
public class BuilderBase<T> {
    public final String modId;
    public final RegistryKey<Registry<T>> registryKey;
    public Map<RegistryKey<T>, T> toBootstrap = new HashMap<>();

    public BuilderBase(String modId, RegistryKey<Registry<T>> registryKey) {
        this.modId = modId;
        this.registryKey = registryKey;
    }

    public RegistryKey<T> register(String name, T object) {
        RegistryKey<T> key = RegistryKey.of(this.registryKey, this.id(name));
        this.toBootstrap.put(key, object);
        return key;
    }

    public void bootstrap(Registerable<T> registerable) {
        this.toBootstrap.forEach(registerable::register);
    }

    public void addEntries(RegistryWrapper.WrapperLookup registries, FabricDynamicRegistryProvider.Entries entries) {
        entries.addAll(registries.getWrapperOrThrow(this.registryKey));
    }

    public Identifier id(String path) {
        return Identifier.of(this.modId, path);
    }
}
