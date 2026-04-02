package net.acoyt.acornlib.api.util;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider.TranslationBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Util;

public class DataUtil {
    // Damage Types
    public static void registerDamageType(TranslationBuilder builder, RegistryKey<DamageType> registryKey, String normal, String item, String player) {
        String key = "death.attack." + registryKey.getValue().getPath();
        builder.add(key, normal);
        builder.add(key + ".item", item);
        builder.add(key + ".player", player);
    }

    // Potion
    public static void registerPotion(TranslationBuilder builder, Potion potion, String formattedName) {
        String id = Registries.POTION.getId(potion).getPath();
        builder.add("item.minecraft.tipped_arrow.effect." + id, "Arrow of " + formattedName);
        builder.add("item.minecraft.potion.effect." + id, "Potion of " + formattedName);
        builder.add("item.minecraft.splash_potion.effect." + id, "Splash Potion of " + formattedName);
        builder.add("item.minecraft.lingering_potion.effect." + id, "Lingering Potion of " + formattedName);
    }

    // Enchantments
    public static void registerEnchantment(TranslationBuilder builder, RegistryKey<Enchantment> registryKey, String name, String desc) {
        String key = "enchantment." + registryKey.getValue().getNamespace() + "." + registryKey.getValue().getPath();
        builder.add(key, name);
        builder.add(key + ".desc", desc);
    }

    public static void registerEnchantment(TranslationBuilder builder, RegistryKey<Enchantment> registryKey, String name) {
        String key = "enchantment." + registryKey.getValue().getNamespace() + "." + registryKey.getValue().getPath();
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

    // Other
    public static String getItemKey(Item item) {
        return Util.createTranslationKey("item", Registries.ITEM.getId(item));
    }

    public static String getItemKey(Item item, String suffix) {
        return getItemKey(item) + "." + suffix;
    }
}
