package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.inventory.AbstractContainerMenu;
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
                    target = "Lnet/minecraft/world/item/ItemStack;isEmpty()Z",
                    ordinal = 2
            )
    )
    private boolean acornlib$cannotDrop(ItemStack instance, Operation<Boolean> original) {
        return original.call(instance) || instance.has(AcornDataComponents.UNDROPPABLE);
    }
}
