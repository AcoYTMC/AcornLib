package net.acoyt.acornlib.mixin.client.hud;

//~ if > 1.21.11 'render' -> 'extract' {
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

//? if > 1.21.10 {
/*import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
import org.spongepowered.asm.mixin.Shadow;
*///? }

/**
 * @author AcoYT
 */
@Mixin(value = Gui.class, priority = 1500)
public abstract class GuiMixin {
    //? if > 1.21.10
    //@Shadow protected abstract Gui.ContextualInfo nextContextualInfoState();

    @Inject(method = "renderCameraOverlays", at = @At("HEAD"), cancellable = true)
    private void acornlib$overlays(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().overlays, ci::cancel);
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void acornlib$crosshair(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().crosshair, ci::cancel);
    }

    @Inject(method = "renderItemHotbar", at = @At("HEAD"), cancellable = true)
    private void acornlib$hotbar(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().hotbar, ci::cancel);
    }

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    private static void acornlib$armor(GuiGraphics graphics, Player player, int yLineBase, int numHealthRows, int healthRowHeight, int xLeft, CallbackInfo ci) {
        if (!KEY.get(player).armor) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHearts", at = @At("HEAD"), cancellable = true)
    private void acornlib$health(GuiGraphics graphics, Player player, int xLeft, int yLineBase, int healthRowHeight, int heartOffsetIndex, float maxHealth, int currentHealth, int oldHealth, int absorption, boolean blink, CallbackInfo ci) {
        ifTrue(!getData().health, ci::cancel);
    }

    @Inject(method = "renderFood", at = @At("HEAD"), cancellable = true)
    private void acornlib$hunger(GuiGraphics graphics, Player player, int yLineBase, int xRight, CallbackInfo ci) {
        ifTrue(!getData().hunger, ci::cancel);
    }

    @WrapOperation(
            //~ if > 1.21.10 'renderPlayerHealth' -> 'renderAirBubbles'
            method = "renderPlayerHealth",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean acornlib$bubbles(Player instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) && KEY.get(instance).bubbles;
    }

    //? if > 1.21.10 {
    /*@WrapOperation(
            method = "renderHotbarAndDecorations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/contextualbar/ContextualBarRenderer;renderBackground(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"
            )
    )
    private void acornlib$experience1(ContextualBarRenderer instance, GuiGraphics graphics, DeltaTracker deltaTracker, Operation<Void> original) {
        if (this.nextContextualInfoState() == Gui.ContextualInfo.EXPERIENCE) {
            ifTrue(getData().experience, () -> original.call(instance, graphics, deltaTracker));
        } else {
            original.call(instance, graphics, deltaTracker);
        }
    }

    @WrapOperation(
            method = "renderHotbarAndDecorations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/contextualbar/ContextualBarRenderer;renderExperienceLevel(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;I)V"
            )
    )
    private void acornlib$experience2(GuiGraphics graphics, Font font, int experienceLevel, Operation<Void> original) {
        ifTrue(getData().experience, () -> original.call(graphics, font, experienceLevel));
    }
    *///? } else {
    @WrapMethod(method = "isExperienceBarVisible")
    private boolean acornlib$experience1(Operation<Boolean> original) {
        return original.call() && getData().experience;
    }
    //? }

    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void acornlib$effects(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().effects, ci::cancel);
    }

    @Inject(
            method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$sidebar(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().sidebar, ci::cancel);
    }

    @Inject(method = "renderTitle", at = @At("HEAD"), cancellable = true)
    private void acornlib$titles(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().titles, ci::cancel);
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void acornlib$chat(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().chat, ci::cancel);
    }

    @Inject(method = "renderTabList", at = @At("HEAD"), cancellable = true)
    private void acornlib$players(GuiGraphics graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().players, ci::cancel);
    }

    @Inject(method = "renderSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void acornlib$tooltip(GuiGraphics graphics, CallbackInfo ci) {
        ifTrue(!getData().tooltip, ci::cancel);
    }

    @Unique
    private AcornData getData() {
        assert Minecraft.getInstance().player != null;
        return KEY.get(Minecraft.getInstance().player);
    }

    @Unique
    private void ifTrue(boolean value, Runnable runnable) {
        if (value) runnable.run();
    }
}
//~ }