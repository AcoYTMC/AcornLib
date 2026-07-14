package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin extends Screen {
    @Shadow @Nullable protected Slot hoveredSlot;

    protected AbstractContainerScreenMixin(Component title) {
        super(title);
    }

    //? if > 1.21.1 {
    @WrapOperation(
            method = "keyPressed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/KeyMapping;matches(Lnet/minecraft/client/input/KeyEvent;)Z",
                    ordinal = 2
            )
    )
    private boolean acornlib$cancelDrop(KeyMapping instance, KeyEvent event, Operation<Boolean> original) {
        return original.call(instance, event) && !this.hoveredSlot.getItem().has(AcornDataComponents.UNDROPPABLE);
    }
    //? } else {
    @WrapOperation(
            method = "keyPressed",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/KeyMapping;matches(II)Z",
                    ordinal = 2
            )
    )
    private boolean acornlib$cancelDrop(KeyMapping instance, KeyEvent event, Operation<Boolean> original) {
        return original.call(instance, event) && !this.hoveredSlot.getItem().has(AcornDataComponents.UNDROPPABLE);
    }
    //? }
}
