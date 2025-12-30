package net.acoyt.acornlib.mixin.event;

import net.acoyt.acornlib.api.event.RenderOverlayEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    @Nullable
    protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Inject(method = "renderMiscOverlays", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getFrozenTicks()I"))
    private void acornlib$miscOverlaysEvent(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        Optional<Identifier> overlayTexture = RenderOverlayEvent.EVENT.invoker().getOverlay(getCameraPlayer());
        overlayTexture.ifPresent(tex -> this.renderOverlay(context, tex, 1.0F));
    }
}
