package net.acoyt.acornlib.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(Inventory.class)
public abstract class InventoryMixin {
    @Shadow public abstract ItemStack getSelectedItem();

    @ModifyReturnValue(method = "removeFromSelected", at = @At("RETURN"))
    private ItemStack acornlib$preventDropping(ItemStack original) {
        return this.getSelectedItem().has(AcornDataComponents.UNDROPPABLE) ? ItemStack.EMPTY : original;
    }
}
