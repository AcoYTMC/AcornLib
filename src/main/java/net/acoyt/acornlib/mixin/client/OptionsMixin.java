package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.impl.networking.serverbound.ChangePerspectivePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author AcoYT
 */
@Mixin(Options.class)
public class OptionsMixin {
    @Shadow protected Minecraft minecraft;

    @Inject(method = "setCameraType", at = @At("TAIL"))
    private void acornlib$storePerspective(CameraType cameraType, CallbackInfo ci) {
        if (this.minecraft.player != null) {
            ClientPlayNetworking.send(new ChangePerspectivePayload(cameraType.name()));
        }
    }
}
