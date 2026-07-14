package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin {
    @WrapOperation(
            method = "slotClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 0
            )
    )
    private boolean acornlib$cannotDrop0(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || instance.has(AcornDataComponents.UNDROPPABLE);
    }

    @WrapOperation(
            method = "slotClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 10
            )
    )
    private boolean acornlib$cannotDrop10(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || instance.has(AcornDataComponents.UNDROPPABLE);
    }


    @WrapOperation(
            method = "slotClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 11
            )
    )
    private boolean acornlib$cannotDrop11(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || instance.has(AcornDataComponents.UNDROPPABLE);
    }
}
