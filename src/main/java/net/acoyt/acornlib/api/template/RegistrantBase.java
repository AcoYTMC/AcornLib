package net.acoyt.acornlib.api.template;

import net.acoyt.acornlib.impl.util.interfaces.IdContained;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
public abstract class RegistrantBase<T> implements IdContained {
    public final List<T> toRegister = new ArrayList<>();
    public final String modId;
    public final Registry<T> registry;

    public RegistrantBase(String modId, Registry<T> registry) {
        this.modId = modId;
        this.registry = registry;
    }

    public <O extends T> O register(String name, O object) {
        this.toRegister.add(object);
        return Registry.register(this.registry, this.id(name), object);
    }

    public Holder<T> registerRef(String name, T object) {
        this.toRegister.add(object);
        return Registry.registerForHolder(this.registry, this.id(name), object);
    }

    public abstract void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder);

    public String getModId() {
        return this.modId;
    }

    public ResourceLocation getId(T object) {
        return this.registry.getKey(object);
    }
}
