package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.impl.index.AcornItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

/**
 * @author AcoYT
 */
public class LootTableModifiers implements LootTableEvents.Modify {
    private static final ResourceLocation OAK_LEAVES_ID = ResourceLocation.withDefaultNamespace("blocks/oak_leaves");

    public static void init() {
        LootTableEvents.MODIFY.register(new LootTableModifiers());
    }

    public void modifyLootTable(ResourceKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, HolderLookup.Provider registries) {
        //~ if >= 1.21.11 'location' -> 'identifier'
        if (OAK_LEAVES_ID.equals(key.location())) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1.0F, 1.0F))
                    .when(LootItemRandomChanceCondition.randomChance(0.03F))
                    .add(LootItem.lootTableItem(AcornItems.ACORN));

            tableBuilder.withPool(poolBuilder);
        }

        if (BuiltInLootTables.ANCIENT_CITY.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1.0F, 2.0F))
                    .when(LootItemRandomChanceCondition.randomChance(0.2F))
                    .add(LootItem.lootTableItem(AcornItems.GOLDEN_ACORN));

            tableBuilder.withPool(poolBuilder);
        }

        if (BuiltInLootTables.DESERT_PYRAMID.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(UniformGenerator.between(1.0F, 2.0F))
                    .when(LootItemRandomChanceCondition.randomChance(0.22F))
                    .add(LootItem.lootTableItem(AcornItems.GOLDEN_ACORN));

            tableBuilder.withPool(poolBuilder);
        }
    }
}
