package net.acoyt.acornlib.mixin.event;

//~ if > 1.21.11 'render' -> 'extract' {
import net.acoyt.acornlib.api.event.RenderOverlayEvent;
import net.minecraft.client.DeltaTracker;
//~ if > 26.1.2 'Gui' -> 'Hud'
import net.minecraft.client.gui.Hud;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

/**
 * @author AcoYT
 */
//~ if > 26.1.2 'Gui' -> 'Hud'
@Mixin(Hud.class)
public abstract class GuiMixin {
    @Shadow @Nullable protected abstract Player getCameraPlayer();
    @Shadow protected abstract void extractTextureOverlay(GuiGraphicsExtractor graphics, Identifier texture, float alpha);

    @Inject(
            method = "extractCameraOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;getTicksFrozen()I"
            )
    )
    private void acornlib$miscOverlaysEvent(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Optional<Identifier> overlayTexture = RenderOverlayEvent.EVENT.invoker().getOverlay(getCameraPlayer());
        overlayTexture.ifPresent(tex -> this.extractTextureOverlay(graphics, tex, 1.0F));
    }
}
//~ }