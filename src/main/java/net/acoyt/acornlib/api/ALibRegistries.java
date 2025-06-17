package net.acoyt.acornlib.api;

import com.mojang.serialization.MapCodec;
import net.acoyt.acornlib.AcornLib;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleType;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.UnaryOperator;

@SuppressWarnings("unused")
public final class ALibRegistries {
    public static Block createBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, AcornLib.cid(name)), factory, settings);
    }

    public static <T> ComponentType<T> createComponent(String name, ComponentType.Builder<T> builder) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, AcornLib.cid(name), builder.build());
    }

    public static <T> ComponentType<T> createEnchantmentEffectComponentType(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, AcornLib.cid(name), builderOperator.apply(ComponentType.builder()).build());
    }

    public static <T extends EnchantmentEntityEffect> MapCodec<T> createEnchantmentEntityEffectType(String name, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, AcornLib.cid(name), codec);
    }

    public static <T extends Entity> EntityType<T> createEntityType(String name, EntityType.Builder<T> builder) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, AcornLib.cid(name));
        return Registry.register(Registries.ENTITY_TYPE, key.getValue(), builder.build(key));
    }

    public static Item createItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, AcornLib.cid(name)), factory, settings);
    }

    public static Item createItem(String name, Function<Item.Settings, Item> factory) {
        return createItem(name, factory, new Item.Settings());
    }

    public static Item createBlockItem(String name, Block block, Item.Settings settings) {
        return createItem(name, s -> new BlockItem(block, s), settings.useBlockPrefixedTranslationKey());
    }

    public static Item createBlockItem(String name, Block block) {
        return createBlockItem(name, block, new Item.Settings());
    }

    public static ItemGroup createItemGroup(String name, ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, AcornLib.cid(name), itemGroup);
    }

    public static ItemGroup createItemGroup(ItemGroup itemGroup) {
        return createItemGroup(AcornLib.currentModId, itemGroup);
    }

    public static <T extends ParticleType<?>> T createParticleType(String name, T particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, AcornLib.cid(name), particleType);
    }

    public static <T extends Potion> T createPotion(String name, T potion) {
        return Registry.register(Registries.POTION, AcornLib.cid(name), potion);
    }

    public static <T extends Recipe<?>> RecipeSerializer<T> createRecipeSerializer(String name, RecipeSerializer<T> recipeSerializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, AcornLib.cid(name), recipeSerializer);
    }

    public static <T extends ScreenHandler> ScreenHandlerType<T> createScreenHandlerType(String name, ScreenHandlerType<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, AcornLib.cid(name), screenHandlerType);
    }

    public static SoundEvent createSoundEvent(String name) {
        Identifier id = AcornLib.cid(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static RegistryEntry<SoundEvent> createSoundEventRef(String name) {
        Identifier id = AcornLib.cid(name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static RegistryEntry<StatusEffect> createStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, AcornLib.cid(name), statusEffect);
    }

    public static KeyBinding createKeybind(String translationKey, int keyCode, String category) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(translationKey, keyCode, category));
    }
}
