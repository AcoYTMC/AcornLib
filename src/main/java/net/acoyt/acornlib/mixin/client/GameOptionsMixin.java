package net.acoyt.acornlib.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Perspective;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Shadow protected MinecraftClient client;

    @Inject(method = "setPerspective", at = @At("TAIL"))
    private void storePerspective(Perspective perspective, CallbackInfo ci) {
        if (this.client.player != null) {
            KEY.get(this.client.player).setStoredPerspective(perspective.name());
        }
    }
}
