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
public class AcornLangGen extends FabricLanguageProvider {
    public AcornLangGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registries, TranslationBuilder builder) {
        // Configs
        registerConfig(builder, "allowSupporterNameColors", "Allow Supporter Name Colors", "Whether or not Supporters/Friends should have custom name colors. \nMeant to provide compat with other mods with custom name colors");
        registerConfig(builder, "displayModIds", "Display Mod IDs", "If mod ids should be shown in mod menu");
        registerConfig(builder, "nameColorCompat", "Name Color Compat", "If the builtin collection of compatible mods should have mod menu name colors.");
        registerCategory(builder, "config", "Config");
        registerCategory(builder, "display", "Display");

        // Subtitles
        builder.add("acornlib.subtitles.aco_plush.honk", "Aco Plush Honks");
        builder.add("acornlib.subtitles.chem_plush.honk", "squarsh");
        builder.add("acornlib.subtitles.gnarp_plush.honk", "Gnarp >:3");
        builder.add("acornlib.subtitles.kio_plush.honk", "Aggressive Chainsaw Noises");
        builder.add("acornlib.subtitles.myth_plush.honk", "RRRAAAAHHHH");
        builder.add("acornlib.subtitles.silly.clairdelune", "Clair de lune");
        builder.add("acornlib.subtitles.toast_plush.honk", "Mrrew :3");

        // Advancements
        builder.add("advancements.acornlib.complete_collection.description", "Only $29.99!");
        builder.add("advancements.acornlib.complete_collection.title", "A Complete Collection");
        builder.add("advancements.acornlib.honk.description", "They so silly :3");
        builder.add("advancements.acornlib.honk.title", "HONK!!!");

        // Attributes
        builder.add("attribute.name.player.opacity", "Opacity");

        // Blocks
        registerPlush(builder, ACO_PLUSH, "Aco Plush", "Blue Hoodie");
        registerPlush(builder, CHEM_PLUSH, "Chem Plush", "Only $9.99!");
        registerPlush(builder, CLOWN_ACO_PLUSH, "Aco Plush", "Clown");
        registerPlush(builder, FESTIVE_ACO_PLUSH, "Aco Plush", "Festive");
        builder.add(GNARP_PLUSH, "Gnarp Plush");
        registerPlush(builder, KIO_PLUSH, "Kio Plush", "Pointless Words, Sharp Swords.");
        builder.add(MYTHORICAL_PLUSH, "Mythorical Plush");
        registerPlush(builder, TOAST_PLUSH, "Toast Plush", "Mrrew :3");

        // Commands
        builder.add("command.acornlib.perspective.get", "Perspective is %s for player %s");
        builder.add("command.acornlib.perspective.empty", "Perspective is empty for player %s");
        builder.add("command.acornlib.perspective.set", "Set perspective to %s for players %s");
        builder.add("command.acornlib.perspective.already_value", "Perspective is already %s for player %s");
        builder.add("command.acornlib.hud_data.set", "Set value for %s to %s");

        // Items
        builder.add(ACORN, "Acorn");
        builder.add(GOLDEN_ACORN, "Golden Acorn");
        builder.add("item.acornlib.test_item", "Test Item");

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
