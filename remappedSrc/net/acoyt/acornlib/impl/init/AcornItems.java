package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.item.TestItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumables;
import java.util.function.Function;

public interface AcornItems {
    Item ACORN = create("acorn", Item::new, new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties(3, 5.0F, false)));

    Item GOLDEN_ACORN = create("golden_acorn", Item::new, new Item.Properties()
            .stacksTo(8)
            .rarity(Rarity.RARE)
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireResistant()
            .food(new FoodProperties(5, 7.0F, true), Consumables.GOLDEN_APPLE));

    Item TEST_ITEM = createDev("test_item", TestItem::new, new Item.Properties()
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

    // Create and Register always
    static Item create(String name, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return Items.registerItem(ResourceKey.create(Registries.ITEM, AcornLib.id(name)), factory, settings);
    }

    // Create and Register when compat mod is loaded, or if the current instance is a development environment, else return null
    static Item createCompat(String name, String modId, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return FabricLoader.getInstance().isModLoaded(modId) || FabricLoader.getInstance().isDevelopmentEnvironment() ? create(name, factory, settings) : null;
    }

    // Create and Register if the current instance is a development environment, else return null
    static Item createDev(String name, Function<Item.Properties, Item> factory, Item.Properties settings) {
        return FabricLoader.getInstance().isDevelopmentEnvironment() ? create(name, factory, settings) : null;
    }

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(ACORN);
            entries.accept(GOLDEN_ACORN);
        });

        // If the current instance is a development environment (the test item is not null), then add it to the item group
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            if (TEST_ITEM != null) {
                entries.accept(TEST_ITEM);
            }
        });
    }
}
