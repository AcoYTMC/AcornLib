package net.acoyt.acornlib.api.util;

import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.component.DataComponentTypes.ENCHANTMENTS;

@SuppressWarnings("unused")
public class ItemUtils {
    /**
     * Changes a specified item's name to a specified color
     * @param item The Item to modify the Name Color of
     * @param nameColor The Decimal color to change the Item's Name Color to
     */
    public static void modifyItemNameColor(Item item, int nameColor) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                Predicate.isEqual(item),
                (builder, item1) -> builder.add(DataComponentTypes.ITEM_NAME, Text.translatable(item1.getTranslationKey()).withColor(nameColor))
        ));
    }

    /**
     * Changes a specified item's name to a specified color
     * @param item The Item to modify the Name Color of
     * @param nameColor The Hex color to change the Item's Name Color to
     */
    public static void modifyItemNameColor(Item item, String nameColor) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                Predicate.isEqual(item),
                (builder, item1) -> builder.add(DataComponentTypes.ITEM_NAME, Text.translatable(item1.getTranslationKey()).withColor(AcornLibUtils.convertToHex(nameColor)))
        ));
    }

    /**
     * Modifies the name color of multiple items
     * @param list A list of Items
     * @param nameColor The Decimal color for each of the item's names
     */
    public static void modifyItemNameColors(List<Item> list, int nameColor) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                list,
                (builder, item1) -> builder.add(DataComponentTypes.ITEM_NAME, Text.translatable(item1.getTranslationKey()).withColor(nameColor))
        ));
    }

    /**
     * Modifies the name color of multiple items
     * @param list A list of Items
     * @param nameColor The Hex color for each of the item's names
     */
    public static void modifyItemNameColors(List<Item> list, String nameColor) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                list,
                (builder, item1) -> builder.add(DataComponentTypes.ITEM_NAME, Text.translatable(item1.getTranslationKey()).withColor(AcornLibUtils.convertToHex(nameColor)))
        ));
    }

    /**
     * @param stack The ItemStack to check for the specified enchantment
     * @param enchantKey The identifier of the enchantment to check for (etc. "minecraft:silk_touch")
     * @return If the item has the enchantment
     */
    public static boolean hasEnchantment(ItemStack stack, String enchantKey) {
        final var enchantments = stack.getOrDefault(ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).getEnchantmentEntries();

        for (final var entry : enchantments) {
            String enchant = entry.getKey().getIdAsString();

            if (enchant.contains(enchantKey)) {
                return true;
            }
        }

        return false;
    }

    public static <T> void addComponentToAllItems(ComponentType<T> type, T value) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                GatheringUtils.getAll(Registries.ITEM),
                (builder, item) -> builder.add(type, value)
        ));
    }
}
