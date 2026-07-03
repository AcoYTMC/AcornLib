package net.acoyt.acornlib.api.template;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public abstract class OrganizedLanguageProvider extends FabricLanguageProvider {
    public OrganizedLanguageProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public OrganizedLanguageProvider(FabricDataOutput output, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, languageCode, registryLookup);
    }

    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
        this.generateAdvancements(provider, builder);
        this.generateBlocks(provider, builder);
        this.generateCommands(provider, builder);
        this.generateConfigs(provider, builder);
        this.generateDamageTypes(provider, builder);
        this.generateEntities(provider, builder);
        this.generateItemGroups(provider, builder);
        this.generateItems(provider, builder);
        this.generateRegistrants(provider, builder);
        this.generateStatusEffects(provider, builder);
        this.generateSubtitles(provider, builder);
        this.generateTags(provider, builder);
        this.generateTexts(provider, builder);
    }

    public void generateAdvancements(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateBlocks(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateCommands(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateConfigs(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateDamageTypes(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateEntities(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateItemGroups(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateItems(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateRegistrants(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateStatusEffects(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateSubtitles(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateTags(HolderLookup.Provider provider, TranslationBuilder builder) {}
    public void generateTexts(HolderLookup.Provider provider, TranslationBuilder builder) {}
}
//~ }