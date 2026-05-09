package net.acoyt.acornlib.data.provider.lang;

import net.acoyt.acornlib.api.template.OrganizedLanguageProvider;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.api.util.DataUtils.registerCategory;
import static net.acoyt.acornlib.api.util.DataUtils.registerConfig;
import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.acoyt.acornlib.impl.index.AcornItems.ACORN;
import static net.acoyt.acornlib.impl.index.AcornItems.GOLDEN_ACORN;

/**
 * @author AcoYT
 */
public class AcornLolCatLangGen extends OrganizedLanguageProvider {
    public AcornLolCatLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "lol_us", registryLookup);
    }

    public void generateAdvancements(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add("advancements.acornlib.complete_collection.description", "for free?? \nno for monies");
        builder.add("advancements.acornlib.complete_collection.title", "all the babies");
        builder.add("advancements.acornlib.honk.description", "we do be :3");
        builder.add("advancements.acornlib.honk.title", "frebby plursh");
    }

    public void generateBlocks(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add(ACO_PLUSH, "silly plursh");
        builder.add(CHEM_PLUSH, "goober plursh");
        builder.add(CLOWN_ACO_PLUSH, "silly plursh");
        builder.add(FESTIVE_ACO_PLUSH, "silly plursh");
        builder.add(GNARP_PLUSH, "gay Plush");
        builder.add(KIO_PLUSH, "musical plursh");
        builder.add(MYTHORICAL_PLUSH, "THE plursh");
        builder.add(TOAST_PLUSH, "kibby plursh");
    }

    public void generateCommands(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add("command.acornlib.perspective.set", "looking all like \"%s\" look at you %s :3");
        builder.add("command.acornlib.perspective.already_value", "you already looking all like \"%s\", come on %s");
    }

    public void generateConfigs(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        registerConfig(builder, "allowSupporterNameColors",
                "fren names", "Whether Supporters/Friends should have custom name colors.",
                AcornLib.MOD_ID
        );
        registerConfig(builder, "displayModIds",
                "may i see your id", "If mod ids should be shown in mod menu",
                AcornLib.MOD_ID
        );
        registerConfig(builder, "nameColorCompat",
                "do they be snazzy", "If the builtin collection of compatible mods should have mod menu name colors.",
                AcornLib.MOD_ID
        );

        registerCategory(builder, "config", "coffin", AcornLib.MOD_ID);
        registerCategory(builder, "display", "i see it", AcornLib.MOD_ID);
    }

    public void generateItems(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add(ACORN, "nut");
        builder.add(GOLDEN_ACORN, "shiny nut");
        builder.add("item.acornlib.test_item", "testing, testing, 1, 2, 3?");
    }

    public void generateSubtitles(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        builder.add("acornlib.subtitles.aco_plush.honk", "hokn");
        builder.add("acornlib.subtitles.chem_plush.honk", "squipsh");
        builder.add("acornlib.subtitles.gnarp_plush.honk", "glorp");
        builder.add("acornlib.subtitles.kio_plush.honk", "i sneezed");
        builder.add("acornlib.subtitles.myth_plush.honk", "i feel so fr!!!");
        builder.add("acornlib.subtitles.silly.clairdelune", "absolute cinema");
        builder.add("acornlib.subtitles.toast_plush.honk", "mrow");
    }

    public void generateTexts(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        // Plush Descriptions
        builder.add(ACO_PLUSH.getTranslationKey() + ".desc", "blu");
        builder.add(CHEM_PLUSH.getTranslationKey() + ".desc", "cheap bebe :3");
        builder.add(CLOWN_ACO_PLUSH.getTranslationKey() + ".desc", "funny");
        builder.add(FESTIVE_ACO_PLUSH.getTranslationKey() + ".desc", "merry crisis!");
        builder.add(KIO_PLUSH.getTranslationKey() + ".desc", "nu uh- ouchie TwT");
        builder.add(TOAST_PLUSH.getTranslationKey() + ".desc", "meow");
    }
}
