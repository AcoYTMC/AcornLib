package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.AcornLibClient;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.CameraType;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow @Nullable public LocalPlayer player;

    @WrapOperation(
            method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/CameraType;cycle()Lnet/minecraft/client/CameraType;"
            )
    )
    private CameraType acornlib$disableManualSwitching(CameraType instance, Operation<CameraType> original) {
        return AcornLibClient.perspectiveSwitching ? original.call(instance) : instance;
    }

    @WrapOperation(
            method = "handleKeybinds",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/KeyMapping;consumeClick()Z",
                    ordinal = 8
            )
    )
    private boolean acornlib$cannotDrop(KeyMapping instance, Operation<Boolean> original) {
        return original.call(instance) && this.player != null && !this.player.getMainHandItem().has(AcornDataComponents.UNDROPPABLE);
    }
}
