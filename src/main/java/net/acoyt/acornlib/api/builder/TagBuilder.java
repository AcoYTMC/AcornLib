package net.acoyt.acornlib.api.builder;

//import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.util.interfaces.IdContained;
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
//public class TagBuilder<T> implements IdContained {
//    public final String modId;
//    public final ResourceKey<Registry<T>> resourceKey;
//    public final List<TagKey<T>> tagKeys = new ArrayList<>();
//
//    public TagBuilder(String modId, ResourceKey<Registry<T>> resourceKey) {
//        this.modId = modId;
//        this.resourceKey = resourceKey;
//    }
//
//    public TagKey<T> register(String name) {
//        TagKey<T> tagKey = TagKey.create(this.resourceKey, this.id(name));
//        this.tagKeys.add(tagKey);
//        return tagKey;
//    }
//
//    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
//        this.tagKeys.forEach(tagKey -> {
//            ResourceLocation tagId = tagKey.location();
//            String prefix = this.resourceKey.identifier().getPath();
//            String key = "tag." + prefix + "." + tagId.getNamespace() + "." + tagId.getPath();
//            builder.add(key, MiscUtils.formatString(tagId.getPath()));
//        });
//    }
//
//    public String getModId() {
//        return this.modId;
//    }
//}
