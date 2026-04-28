package net.acoyt.acornlib.data.provider.lang;

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Util;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.acoyt.acornlib.impl.index.AcornItems.ACORN;
import static net.acoyt.acornlib.impl.index.AcornItems.GOLDEN_ACORN;

/**
 * @author AcoYT
 */
public class AcornLolCatLangGen extends FabricLanguageProvider {
    public AcornLolCatLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "lol_us", registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        // Configs
        registerConfig(builder, "allowSupporterNameColors", "fren names", "Whether or not Supporters/Friends should have custom name colors. \nMeant to provide compat with other mods with custom name colors");
        registerConfig(builder, "displayModIds", "may i see your id", "If mod ids should be shown in mod menu");
        registerConfig(builder, "nameColorCompat", "do they be snazzy", "If the builtin collection of compatible mods should have mod menu name colors.");
        registerCategory(builder, "config", "coffin");
        registerCategory(builder, "display", "i see it");

        // Subtitles
        builder.add("acornlib.subtitles.aco_plush.honk", "hokn");
        builder.add("acornlib.subtitles.chem_plush.honk", "squipsh");
        builder.add("acornlib.subtitles.gnarp_plush.honk", "glorp");
        builder.add("acornlib.subtitles.kio_plush.honk", "i sneezed");
        builder.add("acornlib.subtitles.myth_plush.honk", "i feel so fr!!!");
        builder.add("acornlib.subtitles.silly.clairdelune", "absolute cinema");
        builder.add("acornlib.subtitles.toast_plush.honk", "mrow");

        // Advancements
        builder.add("advancements.acornlib.complete_collection.description", "for free?? \nno for monies");
        builder.add("advancements.acornlib.complete_collection.title", "all the babies");
        builder.add("advancements.acornlib.honk.description", "we do be :3");
        builder.add("advancements.acornlib.honk.title", "frebby plursh");

        // Blocks
        registerPlush(builder, ACO_PLUSH, "silly plursh", "blu");
        registerPlush(builder, CHEM_PLUSH, "goober plursh", "cheap bebe :3");
        registerPlush(builder, CLOWN_ACO_PLUSH, "silly plursh", "funny");
        registerPlush(builder, FESTIVE_ACO_PLUSH, "silly plursh", "merry crisis!");
        builder.add(GNARP_PLUSH, "Gnarp Plush");
        registerPlush(builder, KIO_PLUSH, "musical plursh", "nu uh- ouchie TwT");
        builder.add(MYTHORICAL_PLUSH, "gay plursh");
        registerPlush(builder, TOAST_PLUSH, "kibby plursh", "meow");

        // Commands
        builder.add("command.acornlib.perspective.set", "looking all like \"%s\" look at you %s :3");
        builder.add("command.acornlib.perspective.already_value", "you already looking all like \"%s\", come on %s");

        // Items
        builder.add(ACORN, "nut");
        builder.add(GOLDEN_ACORN, "shiny nut");
        builder.add("item.acornlib.test_item", "testing, testing, 1, 2, 3?");

        // Texts
        builder.add("tooltip.supporter_only", "Cosmetics are for Ko-Fi members and friends.\nIf you'd like access to them, consider supported :3");
    }

    // MidnightConfig
    public void registerConfig(TranslationBuilder builder, String key, String name, String tooltip) {
        key = key.transform(string -> AcornLib.MOD_ID + ".midnightconfig." + string);
        builder.add(key, name);
        builder.add(key + ".tooltip", tooltip);
    }

    public void registerCategory(TranslationBuilder builder, String key, String name) {
        key = key.transform(string -> AcornLib.MOD_ID + ".midnightconfig.category." + string);
        builder.add(key, name);
    }

    // Util
    public void registerPlush(TranslationBuilder builder, Block block, String name, String desc) {
        builder.add(block, name);
        builder.add(Util.createTranslationKey("block", Registries.BLOCK.getId(block)) + ".desc", desc);
    }
}
