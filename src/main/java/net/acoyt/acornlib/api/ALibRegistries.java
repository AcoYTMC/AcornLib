package net.acoyt.acornlib.api;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class ALibRegistries {
    private static String currentModId = "";

    public static ArmorMaterial createArmorMat(int durability, Map<EquipmentType, Integer> defense, int enchantmentValue, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, RegistryKey<EquipmentAsset> assetId) {
        return new ArmorMaterial(durability, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, assetId);
    }
    
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String name, BlockEntityType<T> blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, cid(name), blockEntityType);
    }

    public static Block createBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, cid(name)), factory, settings);
    }

    public static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = createBlock(name, factory, settings);
        createItem(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings().useBlockPrefixedTranslationKey());
        return block;
    }

    public static <T extends ComponentType<?>> T createComponent(String name, T component) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, cid(name), component);
    }

    public static RegistryEntry<StatusEffect> createEffect(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, cid(name), effect);
    }

    public static <T extends Entity> EntityType<T> createEntityType(String name, EntityType.Builder<T> builder) {
        RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, cid(name));
        return Registry.register(Registries.ENTITY_TYPE, key.getValue(), builder.build(key));
    }

    public static ItemGroup createGroup(String name, ItemGroup group) {
        return Registry.register(Registries.ITEM_GROUP, cid(name), group);
    }

    public static ItemGroup createGroup(ItemGroup group) {
        return createGroup(currentModId, group);
    }

    public static Item createItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, cid(name)), factory, settings);
    }

    public static <T extends ParticleType<?>> T createParticle(String name, T particle) {
        return Registry.register(Registries.PARTICLE_TYPE, cid(name), particle);
    }

    public static <T extends ParticleEffect> void createFactory(ParticleType<T> particle, ParticleFactoryRegistry.PendingParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(particle, factory);
    }

    public static SoundEvent createSound(String name) {
        Identifier id = cid(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static ToolMaterial createToolMat(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
        return new ToolMaterial(incorrectBlocksForDrops, durability, miningSpeed, attackDamageBonus, enchantmentValue, repairItems);
    }

    private static Identifier cid(String path) {
        return Identifier.of(currentModId, path);
    }
    
    public static void init(String modId) {
        currentModId = modId;
    }
}