package net.acoyt.acornlib.api;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

@SuppressWarnings("ALL")
public final class ALibRegistries {
    public static String currentModId = "";

    public static ArmorMaterial createArmorMat(
            Map<ArmorItem.Type, Integer> defense,
            int enchantability,
            RegistryEntry<SoundEvent> equipSound,
            Supplier<Ingredient> repairIngredient,
            List<ArmorMaterial.Layer> layers,
            float toughness,
            float knockbackResistance
    ) {
        return new ArmorMaterial(defense, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance);
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntity(String name, FabricBlockEntityTypeBuilder<T> builder) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, cid(name), builder.build());
    }

    public static Block createBlock(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, cid(name), block);
    }

    public static Block createWithItem(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = createBlock(name, factory, settings);
        createItem(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Settings());
        return block;
    }

    public static <T> ComponentType<T> createComponent(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, cid(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    public static RegistryEntry<StatusEffect> createEffect(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, cid(name), effect);
    }

    public static <T extends Entity> EntityType<T> createEntityType(String name, EntityType.Builder<T> builder) {
        return Registry.register(Registries.ENTITY_TYPE, cid(name), builder.build());
    }

    public static ItemGroup createGroup(String name, ItemGroup group) {
        return Registry.register(Registries.ITEM_GROUP, cid(name), group);
    }

    public static ItemGroup createGroup(ItemGroup group) {
        return createGroup(currentModId, group);
    }

    public static Item createItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, cid(name), item);
    }

    public static void create(String name, ParticleType<?> particle) {
        Registry.register(Registries.PARTICLE_TYPE, cid(name), particle);
    }

    public static SoundEvent createSound(String name) {
        Identifier id = cid(name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static ToolMaterial createToolMat(TagKey<Block> incorrectBlocksForDrops, int durability, float miningSpeed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
        return new ToolMaterial() {
            public int getDurability() {
                return durability;
            }

            public float getMiningSpeedMultiplier() {
                return miningSpeed;
            }

            public float getAttackDamage() {
                return attackDamageBonus;
            }

            public TagKey<Block> getInverseTag() {
                return incorrectBlocksForDrops;
            }

            public int getEnchantability() {
                return enchantmentValue;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.fromTag(repairItems);
            }
        };
    }

    public static Identifier cid(String path) {
        return Identifier.of(currentModId, path);
    }

    public static void init(String modId) {
        currentModId = modId;
    }
}
