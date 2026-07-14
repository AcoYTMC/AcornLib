package net.acoyt.acornlib.mixin.event;

//~ if > 1.21.11 'render' -> 'extract' {
import net.acoyt.acornlib.api.event.RenderOverlayEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
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
@Mixin(Gui.class)
public abstract class GuiMixin {
    @Shadow @Nullable protected abstract Player getCameraPlayer();
    @Shadow protected abstract void renderTextureOverlay(GuiGraphics graphics, ResourceLocation texture, float alpha);

    @Inject(
            method = "renderCameraOverlays",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;getTicksFrozen()I"
            )
    )
    private void acornlib$miscOverlaysEvent(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Optional<ResourceLocation> overlayTexture = RenderOverlayEvent.EVENT.invoker().getOverlay(getCameraPlayer());
        overlayTexture.ifPresent(tex -> this.renderTextureOverlay(graphics, tex, 1.0F));
    }
}
//~ }