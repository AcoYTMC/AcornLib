package net.acoyt.acornlib.mixin.client.hud;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

@Mixin(value = InGameHud.class, priority = 1500)
public abstract class InGameHudMixin {
    @TargetHandler(
            mixin = "net.fabricmc.fabric.mixin.client.rendering.InGameHudMixin",
            name = "render"
    )
    @WrapOperation(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/fabricmc/fabric/api/client/rendering/v1/HudRenderCallback;onHudRender(Lnet/minecraft/client/gui/DrawContext;" +
                            "Lnet/minecraft/client/render/RenderTickCounter;)V"
            )
    )
    private void acornlib$events(HudRenderCallback instance, DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        ifTrue(getData().events, () -> original.call(instance, context, tickCounter));
    }

    @Inject(method = "renderMiscOverlays", at = @At("HEAD"), cancellable = true)
    private void acornlib$overlays(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().overlays, ci::cancel);
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void acornlib$crosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().crosshair, ci::cancel);
    }

    @Inject(method = "renderHotbar", at = @At("HEAD"), cancellable = true)
    private void acornlib$hotbar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().hotbar, ci::cancel);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void acornlib$armor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        if (!KEY.get(player).armor) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHealthBar", at = @At("HEAD"), cancellable = true)
    private void acornlib$health(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        ifTrue(!getData().health, ci::cancel);
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void acornlib$hunger(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        ifTrue(!getData().hunger, ci::cancel);
    }

    @WrapOperation(
            method = "renderStatusBars",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;isSubmergedIn(Lnet/minecraft/registry/tag/TagKey;)Z"
            )
    )
    private boolean bubbles(PlayerEntity instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) && KEY.get(instance).bubbles;
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void acornlib$experience1(DrawContext context, int x, CallbackInfo ci) {
        ifTrue(!getData().experience, ci::cancel);
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void acornlib$experience2(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().experience, ci::cancel);
    }

    @Inject(method = "renderStatusEffectOverlay", at = @At("HEAD"), cancellable = true)
    private void acornlib$effects(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().effects, ci::cancel);
    }

    @Inject(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;" +
                    "Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$sidebar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().sidebar, ci::cancel);
    }

    @Inject(method = "renderTitleAndSubtitle", at = @At("HEAD"), cancellable = true)
    private void acornlib$titles(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().titles, ci::cancel);
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void acornlib$chat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().chat, ci::cancel);
    }

    @Inject(method = "renderPlayerList", at = @At("HEAD"), cancellable = true)
    private void acornlib$players(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ifTrue(!getData().players, ci::cancel);
    }

    @Inject(method = "renderHeldItemTooltip", at = @At("HEAD"), cancellable = true)
    private void acornlib$tooltip(DrawContext context, CallbackInfo ci) {
        ifTrue(!getData().tooltip, ci::cancel);
    }

    @Unique
    private AcornData getData() {
        assert MinecraftClient.getInstance().player != null;
        return KEY.get(MinecraftClient.getInstance().player);
    }

    @Unique
    private void ifTrue(boolean value, Runnable runnable) {
        if (value) runnable.run();
    }
}
