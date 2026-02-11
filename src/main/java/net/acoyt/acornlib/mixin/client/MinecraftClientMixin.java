package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.mixin.access.WorldAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public ClientWorld world;

    @WrapOperation(
            method = "handleInputEvents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/option/Perspective;next()Lnet/minecraft/client/option/Perspective;"
            )
    )
    private Perspective disableManualSwitching(Perspective instance, Operation<Perspective> original) {
        if (this.world != null && ((WorldAccessor)this.world).aLib$getProperties() != null && ((WorldAccessor)this.world).aLib$getProperties().getGameRules() != null) {
            return ((WorldAccessor)this.world).aLib$getProperties().getGameRules().getBoolean(AcornGameRules.ALLOW_PERSPECTIVE_CHANGING) ? original.call(instance) : instance;
        }

        return instance;
    }
}
