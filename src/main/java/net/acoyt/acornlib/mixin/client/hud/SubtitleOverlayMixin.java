package net.acoyt.acornlib.mixin.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

/**
 * @author AcoYT
 */
@Mixin(SubtitleOverlay.class)
public class SubtitleOverlayMixin {
    @Shadow @Final private Minecraft minecraft;

    //~ if > 1.21.11 'render' -> 'extractRenderState'
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void acornlib$subtitles(GuiGraphics graphics, CallbackInfo ci) {
        if (this.minecraft.player != null && !KEY.get(this.minecraft.player).subtitles) {
            ci.cancel();
        }
    }
}
