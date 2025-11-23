package net.acoyt.acornlib.api;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.item.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class ALibRegistries {
    private static String currentModId = "";

    public static ArmorMaterial createArmorMat(int durability, Map<ArmorType, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, ResourceKey<EquipmentAsset> assetId) {
        return new ArmorMaterial(durability, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, assetId);
    }
    
    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String name, BlockEntityType<T> blockEntityType) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, cid(name), blockEntityType);
    }

    public static Block createBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return Blocks.register(ResourceKey.create(Registries.BLOCK, cid(name)), factory, settings);
    }

    public static Block createWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Block block = createBlock(name, factory, settings);
        createItem(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Properties().useBlockDescriptionPrefix());
        return block;
    }

    public static <T extends DataComponentType<?>> T createComponent(String name, T component) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, cid(name), component);
    }

    public static Holder<MobEffect> createEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, cid(name), effect);
    }

    public static <T extends Entity> EntityType<T> createEntityType(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, cid(name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key.location(), builder.build(key));
    }

    public static CreativeModeTab createGroup(String name, CreativeModeTab group) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, cid(name), group);
    }

    public static CreativeModeTab createGroup(CreativeModeTab group) {
        return createGroup(currentModId, group);
    }

    public static Item createItem(String name, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return Items.registerItem(ResourceKey.create(Registries.ITEM, cid(name)), factory, settings);
    }

    public static <T extends ParticleType<?>> T createParticle(String name, T particle) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, cid(name), particle);
    }

    public static <T extends ParticleOptions> void createFactory(ParticleType<T> particle, ParticleFactoryRegistry.PendingParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(particle, factory);
    }

    public static SoundEvent createSound(String name) {
        ResourceLocation id = cid(name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static ToolMaterial createToolMat(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
        return new ToolMaterial(incorrectBlocksForDrops, durability, miningSpeed, attackDamageBonus, enchantmentValue, repairItems);
    }

    private static ResourceLocation cid(String path) {
        return ResourceLocation.fromNamespaceAndPath(currentModId, path);
    }
    
    public static void init(String modId) {
        currentModId = modId;
    }
}