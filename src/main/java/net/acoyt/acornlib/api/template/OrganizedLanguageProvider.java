package net.acoyt.acornlib.api.template;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public abstract class OrganizedLanguageProvider extends FabricLanguageProvider {
    public OrganizedLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public OrganizedLanguageProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, languageCode, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        this.generateAdvancements(registries, builder);
        this.generateBlocks(registries, builder);
        this.generateCommands(registries, builder);
        this.generateConfigs(registries, builder);
        this.generateEntities(registries, builder);
        this.generateItems(registries, builder);
        this.generateRegistrants(registries, builder);
        this.generateStatusEffects(registries, builder);
        this.generateSubtitles(registries, builder);
        this.generateTags(registries, builder);
        this.generateTexts(registries, builder);
    }

    public void generateAdvancements(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateBlocks(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateCommands(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateConfigs(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateEntities(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateItems(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateRegistrants(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateStatusEffects(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateSubtitles(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateTags(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
    public void generateTexts(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {}
}
