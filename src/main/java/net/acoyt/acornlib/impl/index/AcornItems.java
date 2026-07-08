package net.acoyt.acornlib.impl.index;

//~ if > 1.21.11 'itemgroup.v1.FabricItemGroupEntries' -> 'creativetab.v1.FabricCreativeModeTabOutput' {
//~ if > 1.21.11 'itemgroup.v1.ItemGroupEvents' -> 'creativetab.v1.CreativeModeTabEvents' {
//~ if > 1.21.11 'FabricItemGroupEntries' -> 'FabricCreativeModeTabOutput' {
//~ if > 1.21.11 'ItemGroupEvents' -> 'CreativeModeTabEvents' {
//~ if > 1.21.11 'ModifyEntries' -> 'ModifyOutput' {
//~ if > 1.21.11 'modifyEntriesEvent' -> 'modifyOutputEvent' {
import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.item.TestItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

//? if > 1.21.3 {
/*import net.minecraft.world.item.component.Consumables;
*///? } else {
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
//? }

import java.util.Optional;

/**
 * @author AcoYT
 */
public interface AcornItems {
    ItemRegistrant ITEMS = new ItemRegistrant(AcornLib.MOD_ID);

    //? if > 1.21.3 {
    /*Item ACORN = ITEMS.register("acorn", Item::new, new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties(3, 5.0F, false)));

    Item GOLDEN_ACORN = ITEMS.register("golden_acorn", Item::new, new Item.Properties()
            .stacksTo(8)
            .rarity(Rarity.RARE)
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireResistant()
            .food(new FoodProperties(5, 7.0F, true), Consumables.GOLDEN_APPLE));
    *///? } else {
    Item ACORN = ITEMS.register("acorn", Item::new, new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()));

    Item GOLDEN_ACORN = ITEMS.register("golden_acorn", Item::new, new Item.Properties()
            .stacksTo(8)
            .rarity(Rarity.RARE)
            .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireResistant()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .saturationModifier(1.2F)
                    .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F)
                    .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F)
                    .alwaysEdible()
                    .build()));
    //? }

    static void init() {
        MiscUtils.ifDev(() -> {
            ITEMS.register("test_item", TestItem::new, new Item.Properties().component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));
            ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
                Optional<Item> item = BuiltInRegistries.ITEM.getOptional(AcornLib.id("test_item"));
                item.ifPresent(entries::accept);
            });
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(AcornItems::addFoodEntries);
    }

    private static void addFoodEntries(FabricItemGroupEntries entries) {
        entries.accept(ACORN);
        entries.accept(GOLDEN_ACORN);
    }
}
//~}
//~}
//~}
//~}
//~}
//~}