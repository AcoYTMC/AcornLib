package net.acoyt.acornlib.mixin.immovable;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.*;
import net.minecraft.world.inventory.BrewingStandMenu.FuelSlot;
import net.minecraft.world.inventory.BrewingStandMenu.IngredientsSlot;
import net.minecraft.world.inventory.BrewingStandMenu.PotionSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author AcoYT
 */
@Mixin(value = {
        ArmorSlot.class,
        FurnaceFuelSlot.class,
        BeaconMenu.PaymentSlot.class,
        ShulkerBoxSlot.class,
        FuelSlot.class, IngredientsSlot.class, PotionSlot.class
})
public abstract class GeneralSlotMixin extends Slot {
    public GeneralSlotMixin(Container inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @WrapMethod(method = "mayPlace")
    private boolean acornlib$cannotInsert(ItemStack itemStack, Operation<Boolean> original) {
        if (!(this.container instanceof Inventory) && itemStack.has(AcornDataComponents.IMMOVABLE)) {
            return false;
        }

        return original.call(itemStack);
    }
}
