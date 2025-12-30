package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.item.TestItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public interface AcornItems {
    Item ACORN = create("acorn", Item::new, new Item.Settings()
            .maxCount(16)
            .food(new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(0.5F)
                    .build()));

    Item GOLDEN_ACORN = create("golden_acorn", Item::new, new Item.Settings()
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

    static Item create(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return Registry.register(Registries.ITEM, AcornLib.id(name), item);
    }

    static void init() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            create("test_item", TestItem::new, new Item.Settings().component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true));
        }

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(ACORN);
            entries.add(GOLDEN_ACORN);
        });
    }
}
