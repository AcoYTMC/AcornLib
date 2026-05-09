package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.item.TestItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Rarity;

import java.util.Optional;

public interface AcornItems {
    ItemRegistrant ITEMS = new ItemRegistrant(AcornLib.MOD_ID);

    Item ACORN = ITEMS.register("acorn", Item::new, new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()));

    Item GOLDEN_ACORN = ITEMS.register("golden_acorn", Item::new, new Item.Settings()
            .maxCount(8)
            .rarity(Rarity.RARE)
            .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
            .fireproof()
            .food(new FoodComponent.Builder()
                    .nutrition(5)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
                    .alwaysEdible()
                    .build()));

    static void init() {
        MiscUtils.ifDev(() -> {
            ITEMS.register("test_item", TestItem::new, new Item.Settings().component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true));
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
                Optional<Item> item = Registries.ITEM.getOrEmpty(AcornLib.id("test_item"));
                item.ifPresent(entries::add);
            });
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ACORN);
            entries.add(GOLDEN_ACORN);
        });
    }
}
