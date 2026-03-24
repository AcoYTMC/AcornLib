package net.acoyt.acornlib.mixin.client.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.SubtitlesHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

@Mixin(SubtitlesHud.class)
public class SubtitlesHudMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void acornlib$subtitles(DrawContext context, CallbackInfo ci) {
        if (this.client.player != null && !KEY.get(this.client.player).subtitles) {
            ci.cancel();
        }
    }
}
