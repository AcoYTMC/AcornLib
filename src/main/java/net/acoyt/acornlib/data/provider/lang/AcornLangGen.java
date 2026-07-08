package net.acoyt.acornlib.data.provider.lang;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
import net.acoyt.acornlib.api.template.OrganizedLanguageProvider;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.acoyt.acornlib.impl.index.AcornBlocks;
import net.acoyt.acornlib.impl.index.AcornItems;
import net.acoyt.acornlib.impl.index.tag.AcornBlockTags;
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.api.util.DataUtils.registerCategory;
import static net.acoyt.acornlib.api.util.DataUtils.registerConfig;
import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

//? if > 1.21.10 {
/*import net.acoyt.acornlib.impl.index.AcornGameRules;
*///? }

/**
 * @author AcoYT
 */
public class AcornLangGen extends OrganizedLanguageProvider {
    public AcornLangGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancements(HolderLookup.Provider registries, TranslationBuilder builder) {
        builder.add("advancements.acornlib.complete_collection.description", "Only $29.99!");
        builder.add("advancements.acornlib.complete_collection.title", "A Complete Collection");
        builder.add("advancements.acornlib.honk.description", "They so silly :3");
        builder.add("advancements.acornlib.honk.title", "HONK!!!");
    }

    public void generateCommands(HolderLookup.Provider registries, TranslationBuilder builder) {
        builder.add("command.acornlib.perspective.get", "Perspective is %s for player %s");
        builder.add("command.acornlib.perspective.empty", "Perspective is empty for player %s");
        builder.add("command.acornlib.perspective.set", "Set perspective to %s for players %s");
        builder.add("command.acornlib.perspective.already_value", "Perspective is already %s for player %s");
        builder.add("command.acornlib.hud_data.set", "Set value for %s to %s");
    }

    public void generateConfigs(HolderLookup.Provider registries, TranslationBuilder builder) {
        registerConfig(builder, "allowSupporterNameColors",
                "Allow Supporter Name Colors", "Whether Supporters/Friends should have custom name colors.",
                AcornLib.MOD_ID
        );
        registerConfig(builder, "displayModIds",
                "Display Mod IDs", "If mod ids should be shown in mod menu",
                AcornLib.MOD_ID
        );
        registerConfig(builder, "nameColorCompat",
                "Name Color Compat", "If the builtin collection of compatible mods should have mod menu name colors.",
                AcornLib.MOD_ID
        );

        registerCategory(builder, "config", "Config", AcornLib.MOD_ID);
        registerCategory(builder, "display", "Display", AcornLib.MOD_ID);
    }

    public void generateRegistrants(HolderLookup.Provider registries, TranslationBuilder builder) {
        AcornBlockTags.BLOCKS.registerLang(registries, builder);
        AcornItemTags.ITEMS.registerLang(registries, builder);

        AcornAttributes.ATTRIBUTES.registerLang(registries, builder);
        AcornBlocks.BLOCKS.registerLang(registries, builder);
        //? if > 1.21.10 {
        /*AcornGameRules.GAME_RULES.registerLang(registries, builder);
        *///? }
        AcornItems.ITEMS.registerLang(registries, builder);
    }

    public void generateSubtitles(HolderLookup.Provider registries, TranslationBuilder builder) {
        builder.add("acornlib.subtitles.aco_plush.honk", "Aco Plush Honks");
        builder.add("acornlib.subtitles.chem_plush.honk", "squarsh");
        builder.add("acornlib.subtitles.gnarp_plush.honk", "Gnarp >:3");
        builder.add("acornlib.subtitles.kio_plush.honk", "Aggressive Chainsaw Noises");
        builder.add("acornlib.subtitles.myth_plush.honk", "RRRAAAAHHHH");
        builder.add("acornlib.subtitles.silly.clairdelune", "Clair de lune");
        builder.add("acornlib.subtitles.toast_plush.honk", "Mrrew :3");
    }

    public void generateTexts(HolderLookup.Provider registries, TranslationBuilder builder) {
        // Plush Descriptions
        builder.add(ACO_PLUSH.getDescriptionId() + ".desc", "Blue Hoodie");
        builder.add(CHEM_PLUSH.getDescriptionId() + ".desc", "Only $9.99!");
        builder.add(CLOWN_ACO_PLUSH.getDescriptionId() + ".desc", "Clown");
        builder.add(FESTIVE_ACO_PLUSH.getDescriptionId() + ".desc", "Festive");
        builder.add(KIO_PLUSH.getDescriptionId() + ".desc", "Pointless Words, Sharp Swords.");
        builder.add(TOAST_PLUSH.getDescriptionId() + ".desc", "Mrrew :3");

        // Texts
        builder.add("tooltip.acornlib.supporter_only", "You must be an AcoYT Supporter to use this feature! \nPlease consider supporting :3");
        builder.add("tooltip.acornlib.item_skin", "Skin: %1$s");
        builder.add("tooltip.acornlib.unregistered", "Unregistered");
    }
}
//~ }