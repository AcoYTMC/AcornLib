package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.item.TestItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.Consumables;

import java.util.Optional;

/**
 * @author AcoYT
 */
public interface AcornItems {
    ItemRegistrant ITEMS = new ItemRegistrant(AcornLib.MOD_ID);

    Item ACORN = ITEMS.register("acorn", Item::new, new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties(3, 5.0F, false)));

    Item GOLDEN_ACORN = ITEMS.register("golden_acorn", Item::new, new Item.Properties()
            .stacksTo(8)
            .rarity(Rarity.RARE)
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireResistant()
            .food(new FoodProperties(5, 7.0F, true), Consumables.GOLDEN_APPLE));

    static void init() {
        MiscUtils.ifDev(() -> {
            ITEMS.register("test_item", TestItem::new, new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));
            CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
                Optional<Item> item = BuiltInRegistries.ITEM.getOptional(AcornLib.id("test_item"));
                item.ifPresent(entries::accept);
            });
        });

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.accept(ACORN);
            entries.accept(GOLDEN_ACORN);
        });
    }
}
