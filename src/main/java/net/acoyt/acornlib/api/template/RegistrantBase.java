package net.acoyt.acornlib.api.template;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
public abstract class RegistrantBase<T> {
    public final List<T> toRegister = new ArrayList<>();
    private final String modId;
    private final Registry<T> registry;

    public RegistrantBase(String modId, Registry<T> registry) {
        this.modId = modId;
        this.registry = registry;
    }

    public <O extends T> O register(String name, O object) {
        this.toRegister.add(object);
        return Registry.register(this.registry, id(name), object);
    }

    public RegistryEntry<T> registerRef(String name, T object) {
        this.toRegister.add(object);
        return Registry.registerReference(this.registry, id(name), object);
    }

    public abstract void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder);

    public Identifier id(String path) {
        return Identifier.of(this.modId, path);
    }

    public Identifier getId(T object) {
        return this.registry.getId(object);
    }
}
