package net.acoyt.acornlib.api.builder;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.util.interfaces.IdContained;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
public class TagBuilder<T> implements IdContained {
    public final String modId;
    public final RegistryKey<Registry<T>> registryKey;
    public final List<TagKey<T>> tagKeys = new ArrayList<>();

    public TagBuilder(String modId, RegistryKey<Registry<T>> registryKey) {
        this.modId = modId;
        this.registryKey = registryKey;
    }

    public TagKey<T> register(String name) {
        TagKey<T> tagKey = TagKey.of(this.registryKey, this.id(name));
        this.tagKeys.add(tagKey);
        return tagKey;
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.tagKeys.forEach(tagKey -> {
            Identifier tagId = tagKey.id();
            String prefix = this.registryKey.getValue().getPath();
            String key = "tag." + prefix + "." + tagId.getNamespace() + "." + tagId.getPath();
            builder.add(key, MiscUtils.formatString(tagId.getPath()));
        });
    }

    public String getModId() {
        return this.modId;
    }
}
