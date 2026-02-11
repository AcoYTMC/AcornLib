package net.acoyt.acornlib.compat;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.Optional;

public class TrinketsCompat {
    public static Optional<ItemStack> getPlushTrinket(LivingEntity living) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(living);
        if (optional.isEmpty()) return Optional.empty();

        TrinketComponent component = optional.get();
        for (Pair<SlotReference, ItemStack> pair : component.getEquipped(stack -> stack.isIn(AcornItemTags.PLUSHIES))) {
            if (pair.getLeft().inventory().getSlotType().getName().equals("hat") && pair.getRight().isIn(AcornItemTags.PLUSHIES)) {
                return Optional.of(pair.getRight());
            }
        }

        return Optional.empty();
    }

    public static boolean hasPlushTrinket(LivingEntity living) {
        return getPlushTrinket(living).isPresent();
    }
}
