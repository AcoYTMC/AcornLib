package net.acoyt.acornlib.api.builder;

import net.acoyt.acornlib.impl.util.interfaces.IdContained;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author AcoYT
 */
//public class KeyedBuilder<T> implements IdContained {
//    public final String modId;
//    public final ResourceKey<Registry<T>> resourceKey;
//    public Map<ResourceKey<T>, T> toBootstrap = new HashMap<>();
//    public Map<ResourceKey<T>, Function<BootstrapContext<T>, T>> functions = new HashMap<>();
//
//    public KeyedBuilder(String modId, ResourceKey<Registry<T>> resourceKey) {
//        this.modId = modId;
//        this.resourceKey = resourceKey;
//    }
//
//    public ResourceKey<T> register(String name, T object) {
//        ResourceKey<T> key = ResourceKey.create(this.resourceKey, this.id(name));
//        this.toBootstrap.put(key, object);
//        return key;
//    }
//
//    public ResourceKey<T> register(String name, Function<BootstrapContext<T>, T> function) {
//        ResourceKey<T> key = ResourceKey.create(this.resourceKey, this.id(name));
//        this.functions.put(key, function);
//        return key;
//    }
//
//    public void bootstrap(BootstrapContext<T> context) {
//        this.functions.forEach((key, function) -> context.register(key, function.apply(context)));
//        this.toBootstrap.forEach(context::register);
//    }
//
//    public void addEntries(HolderLookup.Provider provider, FabricDynamicRegistryProvider.Entries entries) {
//        entries.addAll(provider.lookupOrThrow(this.resourceKey));
//    }
//
//    public String getModId() {
//        return this.modId;
//    }
//}
