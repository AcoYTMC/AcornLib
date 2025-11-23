package net.acoyt.acornlib.api.util;

import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import java.util.List;
import java.util.function.Predicate;

import static net.minecraft.core.component.DataComponents.ENCHANTMENTS;

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
                (builder, item1) -> builder.set(DataComponents.ITEM_NAME, Component.translatable(item1.getDescriptionId()).withColor(nameColor))
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
                (builder, item1) -> builder.set(DataComponents.ITEM_NAME, Component.translatable(item1.getDescriptionId()).withColor(AcornLibUtils.convertToHex(nameColor)))
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
                (builder, item1) -> builder.set(DataComponents.ITEM_NAME, Component.translatable(item1.getDescriptionId()).withColor(nameColor))
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
                (builder, item1) -> builder.set(DataComponents.ITEM_NAME, Component.translatable(item1.getDescriptionId()).withColor(AcornLibUtils.convertToHex(nameColor)))
        ));
    }

    /**
     * @param stack The ItemStack to check for the specified enchantment
     * @param enchantKey The identifier of the enchantment to check for (etc. "minecraft:silk_touch")
     * @return If the item has the enchantment
     */
    public static boolean hasEnchantment(ItemStack stack, String enchantKey) {
        final var enchantments = stack.getOrDefault(ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet();

        for (final var entry : enchantments) {
            String enchant = entry.getKey().getRegisteredName();

            if (enchant.contains(enchantKey)) {
                return true;
            }
        }

        return false;
    }

    public static <T> void addComponentToAllItems(DataComponentType<T> type, T value) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                GatheringUtils.getAll(BuiltInRegistries.ITEM),
                (builder, item) -> builder.set(type, value)
        ));
    }
}
