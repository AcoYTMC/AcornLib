package net.acoyt.acornlib.mixin.event;

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

@Mixin(Gui.class)
public abstract class InGameHudMixin {
    @Shadow
    @Nullable
    protected abstract Player getCameraPlayer();

    @Shadow
    protected abstract void renderOverlay(GuiGraphics context, ResourceLocation texture, float opacity);

    @Inject(method = "renderMiscOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getFrozenTicks()I"))
    private void acornlib$miscOverlaysEvent(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Optional<ResourceLocation> overlayTexture = RenderOverlayEvent.EVENT.invoker().getOverlay(getCameraPlayer());
        overlayTexture.ifPresent(tex -> this.renderOverlay(context, tex, 1.0F));
    }
}
