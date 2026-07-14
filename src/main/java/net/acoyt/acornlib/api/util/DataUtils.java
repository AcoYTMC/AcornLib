package net.acoyt.acornlib.api.util;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;

//? if > 1.21.11 {
 /*import net.minecraft.client.resources.model.sprite.Material;
 *///? }

//? if > 1.21.10 {
/*import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.util.Util;

import java.util.Arrays;
*///? } else {
import net.minecraft.Util;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
//? }

/**
 * @author AcoYT
 */
@SuppressWarnings("unused")
public class DataUtils {
    // Damage Types
    public static void registerDamageType(TranslationBuilder builder, ResourceKey<DamageType> registryKey, String normal, String item, String player) {
        //~ if >= 1.21.11 'location' -> 'identifier'
        String key = "death.attack." + registryKey.location().getPath();
        builder.add(key, normal);
        builder.add(key + ".item", item);
        builder.add(key + ".player", player);
    }

    public static void registerDamageTypeAll(TranslationBuilder builder, ResourceKey<DamageType> registryKey, String all) {
        //~ if >= 1.21.11 'location' -> 'identifier'
        String key = "death.attack." + registryKey.location().getPath();
        builder.add(key, all);
        builder.add(key + ".item", all);
        builder.add(key + ".player", all);
    }

    // Potion
    public static void registerPotion(TranslationBuilder builder, Potion potion, String formattedName) {
        String id = BuiltInRegistries.POTION.getKey(potion).getPath();
        builder.add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + formattedName);
        builder.add("item.minecraft.potion.effect." + id, "Potion of " + formattedName);
        builder.add("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + formattedName);
        builder.add("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + formattedName);
    }

    // Enchantments
    public static void registerEnchantment(TranslationBuilder builder, ResourceKey<Enchantment> registryKey, String name, String desc) {
        //~ if >= 1.21.11 'location' -> 'identifier'
        String key = "enchantment." + registryKey.location().getNamespace() + "." + registryKey.location().getPath();
        builder.add(key, name);
        builder.add(key + ".desc", desc);
    }

    public static void registerEnchantment(TranslationBuilder builder, ResourceKey<Enchantment> registryKey, String name) {
        //~ if >= 1.21.11 'location' -> 'identifier'
        String key = "enchantment." + registryKey.location().getNamespace() + "." + registryKey.location().getPath();
        builder.add(key, name);
    }

    // MidnightConfig
    public static void registerConfig(TranslationBuilder builder, String key, String name, String tooltip, String modId) {
        key = key.transform(string -> modId + ".midnightconfig." + string);
        builder.add(key, name);
        builder.add(key + ".tooltip", tooltip);
    }

    public static void registerConfig(TranslationBuilder builder, String key, String name, String modId) {
        key = key.transform(string -> modId + ".midnightconfig." + string);
        builder.add(key, name);
    }

    public static void registerCategory(TranslationBuilder builder, String key, String name, String modId) {
        key = key.transform(string -> modId + ".midnightconfig.category." + string);
        builder.add(key, name);
    }

    // Models
    //? if > 1.21.4 {
    /*public static void createSimpleGuiVarying(ItemModelGenerators generators, Item item, ModelTemplate inHandModel) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        ResourceLocation inHandId = id.withPath(st -> "item/" + st + "_in_hand");

        generators.createFlatItemModel(item, ModelTemplates.FLAT_ITEM);
        //? if > 1.21.11 {
        /^inHandModel.create(inHandId, TextureMapping.layer0(new Material(inHandId)), generators.modelOutput);
         ^///? } else {
        inHandModel.create(inHandId, TextureMapping.layer0(inHandId), generators.modelOutput);
         //? }
        generators.itemModelOutput.accept(item,
                ItemModelUtils.select(
                        new DisplayContext(),
                        ItemModelUtils.plainModel(id.withPath(st -> "item/" + st + "_in_hand")),
                        ItemModelUtils.when(
                                Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                                ItemModelUtils.plainModel(id.withPath(st -> "item/" + st))
                        )
                )
        );
    }

    public static void createSimpleGuiVarying(ItemModelGenerators generators, Item item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);

        generators.createFlatItemModel(item, ModelTemplates.FLAT_ITEM);
        generators.itemModelOutput.accept(item,
                ItemModelUtils.select(
                        new DisplayContext(),
                        ItemModelUtils.plainModel(id.withPath(st -> "item/" + st + "_in_hand")),
                        ItemModelUtils.when(
                                Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                                ItemModelUtils.plainModel(id.withPath(st -> "item/" + st))
                        )
                )
        );
    }
     *///? } else {
    public static void createSimpleGuiVarying(ItemModelGenerators generators, Item item, ModelTemplate inHandModel) {
        ResourceLocation location = ModelLocationUtils.getModelLocation(item);
        ResourceLocation inHandLocation = location.withSuffix("_in_hand");

        ModelTemplates.FLAT_ITEM.create(location, TextureMapping.layer0(location), generators.output);
        inHandModel.create(inHandLocation, TextureMapping.layer0(inHandLocation), generators.output);
    }
    //? }

    // Other
    public static String getItemKey(Item item) {
        return Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(item));
    }

    public static String getItemKey(Item item, String suffix) {
        return getItemKey(item) + "." + suffix;
    }
}
