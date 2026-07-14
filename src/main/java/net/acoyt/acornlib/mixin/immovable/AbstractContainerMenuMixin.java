package net.acoyt.acornlib.mixin.immovable;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {
    @WrapOperation(
            method = "doClick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/Slot;mayPlace(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean acornlib$cannotInsertSlotClick(Slot instance, ItemStack itemStack, Operation<Boolean> original) {
        if (!(instance.container instanceof Inventory) && itemStack.has(AcornDataComponents.IMMOVABLE)) {
            return false;
        }

        return original.call(instance, itemStack);
    }

    @WrapOperation(
            method = "moveItemStackTo",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/Slot;mayPlace(Lnet/minecraft/world/item/ItemStack;)Z"
            )
    )
    private boolean acornlib$cannotInsert(Slot instance, ItemStack itemStack, Operation<Boolean> original) {
        if (!(instance.container instanceof Inventory) && itemStack.has(AcornDataComponents.IMMOVABLE)) {
            return false;
        }

        return original.call(instance, itemStack);
    }
}
