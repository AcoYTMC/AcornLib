package net.acoyt.acornlib.init;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.item.TestItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public interface AcornItems {
    Item ACORN = create("acorn", Item::new, new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent(3, 5.0F, false)));

    Item GOLDEN_ACORN = create("golden_acorn", Item::new, new Item.Settings()
            .maxCount(8)
            .rarity(Rarity.RARE)
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireproof()
            .food(new FoodComponent(5, 7.0F, true), ConsumableComponents.GOLDEN_APPLE));

    Item TEST_ITEM = createDev("test_item", TestItem::new, new Item.Settings()
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true));

    // Create and Register always
    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, AcornLib.id(name)), factory, settings);
    }

    // Create and Register when compat mod is loaded, or if the current instance is a development environment, else return null
    static Item createCompat(String name, String modId, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return FabricLoader.getInstance().isModLoaded(modId) || FabricLoader.getInstance().isDevelopmentEnvironment() ? create(name, factory, settings) : null;
    }

    // Create and Register if the current instance is a development environment, else return null
    static Item createDev(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? create(name, factory, settings) : null;
    }

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ACORN);
            entries.add(GOLDEN_ACORN);
        });

        // If the current instance is a development environment (the test item is not null), then add it to the item group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            if (TEST_ITEM != null) {
                entries.add(TEST_ITEM);
            }
        });
    }
}
