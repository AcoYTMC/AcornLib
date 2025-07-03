package net.acoyt.acornlib.init;

import net.acoyt.acornlib.AcornLib;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(RegistryKey.of(RegistryKeys.ITEM, AcornLib.id(name)), factory, settings);
    }

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ACORN);
            entries.add(GOLDEN_ACORN);
        });
    }
}
