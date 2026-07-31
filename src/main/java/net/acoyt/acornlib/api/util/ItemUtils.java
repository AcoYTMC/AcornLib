package net.acoyt.acornlib.api.util;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static net.minecraft.core.component.DataComponents.ENCHANTMENTS;

/**
 * @author AcoYT
 */
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
                (builder, item1) -> {
                    MutableComponent text = Component.translatable(item1.getDescriptionId());
                    builder.set(DataComponents.ITEM_NAME, builder.getOrDefault(DataComponents.ITEM_NAME, text).copy().withColor(nameColor));
                }
        ));
    }

    /**
     * Changes a specified item's name to a specified color
     * @param item The Item to modify the Name Color of
     * @param nameColor The Hex color to change the Item's Name Color to
     */
    public static void modifyItemNameColor(Item item, String nameColor) {
        modifyItemNameColor(item, AcornUtil.convertToHex(nameColor));
    }

    /**
     * Modifies the name color of multiple items
     * @param list A list of Items
     * @param nameColor The Decimal color for each of the item's names
     */
    public static void modifyItemNameColors(List<Item> list, int nameColor) {
        list.forEach(item -> modifyItemNameColor(item, nameColor));
    }

    /**
     * Modifies the name color of multiple items
     * @param list A list of Items
     * @param nameColor The Hex color for each of the item's names
     */
    public static void modifyItemNameColors(List<Item> list, String nameColor) {
        list.forEach(item -> modifyItemNameColor(item, nameColor));
    }

    /**
     * @param stack The ItemStack to check for the specified enchantment
     * @param enchantKey The identifier of the enchantment to check for (etc. "minecraft:silk_touch")
     * @return Whether the item has the enchantment
     */
    public static boolean hasEnchantment(ItemStack stack, String enchantKey) {
        Set<Entry<Holder<Enchantment>>> enchantments = stack.getOrDefault(ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet();
        for (Entry<Holder<Enchantment>> entry : enchantments) {
            String enchant = entry.getKey().getRegisteredName();

            if (enchant.contains(enchantKey)) {
                return true;
            }
        }

        return false;
    }

    public static void enchantStack(Level level, ItemStack stack, String enchantKey, int enchantLevel) {
        List<Reference<Enchantment>> enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).listElements().toList();

        for (Reference<Enchantment> enchant : enchantments) {
            if (enchant.key().equals(ResourceKey.create(Registries.ENCHANTMENT, Identifier.parse(enchantKey)))) {
                stack.enchant(enchant, enchantLevel);
            }
        }
    }

    public static void enchantStack(Level level, ItemStack stack, String enchantKey) {
        enchantStack(level, stack, enchantKey, 1);
    }

    public static int getLevel(Level level, ItemStack stack, String enchantKey) {
        return hasEnchantment(stack, enchantKey) ?
                EnchantmentHelper.getEnchantmentsForCrafting(stack)
                        .getLevel(level.registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(ResourceKey.create(Registries.ENCHANTMENT, Identifier.parse(enchantKey)))
                        )
                : 0;
    }

    public static <T> void addComponentToAllItems(DataComponentType<T> type, T value) {
        DefaultItemComponentEvents.MODIFY.register(ctx -> ctx.modify(
                BuiltInRegistries.ITEM.stream().toList(),
                (builder, item) -> builder.set(type, value)
        ));
    }

    public static List<ItemStack> getHeldStacks(LivingEntity living) {
        List<ItemStack> stacks = new ArrayList<>();
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = living.getItemInHand(hand);
            if (!stack.isEmpty()) {
                stacks.add(stack);
            }
        }

        return stacks;
    }
}
