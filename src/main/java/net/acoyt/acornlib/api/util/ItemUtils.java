package net.acoyt.acornlib.api.util;

import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;
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
     * @return Whether the item has the enchantment
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

    public static void enchantStack(World world, ItemStack stack, String enchantKey, int level) {
        var enchantments = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT).streamEntries().toList();
        for (var enchant : enchantments) {
            if (enchant.registryKey().equals(RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(enchantKey)))) {
                stack.addEnchantment(enchant, level);
            }
        }
    }

    public static void enchantStack(World world, ItemStack stack, String enchantKey) {
        enchantStack(world, stack, enchantKey, 1);
    }

    public static int getLevel(World world, ItemStack stack, String enchantKey) {
        return hasEnchantment(stack, enchantKey) ?
                EnchantmentHelper.getEnchantments(stack).getLevel(
                        world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(enchantKey))))
                : 0;
    }

    public static <T> void addComponentToAllItems(ComponentType<T> type, T value) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                GatheringUtils.getAll(Registries.ITEM),
                (builder, item) -> builder.add(type, value)
        ));
    }

    public static List<ItemStack> getHeldStacks(PlayerEntity player) {
        List<ItemStack> stacks = new ArrayList<>();
        for (Hand hand : Hand.values()) {
            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }

        return stacks;
    }
}
