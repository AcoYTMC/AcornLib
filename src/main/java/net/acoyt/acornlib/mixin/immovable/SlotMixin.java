package net.acoyt.acornlib.mixin.immovable;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author AcoYT
 */
@Mixin(Slot.class)
public abstract class SlotMixin {
    @Shadow @Final public Container container;

    @WrapMethod(method = "mayPlace")
    private boolean acornlib$cannotInsert(ItemStack itemStack, Operation<Boolean> original) {
        if (!(this.container instanceof Inventory) && itemStack.has(AcornDataComponents.IMMOVABLE)) {
            return false;
        }

        return original.call(itemStack);
    }
}
