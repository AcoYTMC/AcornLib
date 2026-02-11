package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.impl.index.AcornItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class LootTableModifiers implements LootTableEvents.Modify {
    private static final Identifier OAK_LEAVES_ID = Identifier.ofVanilla("blocks/oak_leaves");

    public static void init() {
        LootTableEvents.MODIFY.register(new LootTableModifiers());
    }

    public void modifyLootTable(RegistryKey<LootTable> key, LootTable.Builder tableBuilder, LootTableSource source, RegistryWrapper.WrapperLookup registries) {
        if (OAK_LEAVES_ID.equals(key.getValue())) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0F, 1.0F))
                    .conditionally(RandomChanceLootCondition.builder(0.05F))
                    .with(ItemEntry.builder(AcornItems.ACORN));

            tableBuilder.pool(poolBuilder);
        }

        if (LootTables.ANCIENT_CITY_CHEST.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                    .conditionally(RandomChanceLootCondition.builder(0.25F))
                    .with(ItemEntry.builder(AcornItems.GOLDEN_ACORN));

            tableBuilder.pool(poolBuilder);
        }

        if (LootTables.DESERT_PYRAMID_CHEST.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(UniformLootNumberProvider.create(1.0F, 2.0F))
                    .conditionally(RandomChanceLootCondition.builder(0.2F))
                    .with(ItemEntry.builder(AcornItems.GOLDEN_ACORN));

            tableBuilder.pool(poolBuilder);
        }
    }
}
