package net.acoyt.acornlib.mixin.client.hud;

//~ if > 1.21.11 'render' -> 'extract' {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
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
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.Font;
//? } else {
/*import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
*///? }

/**
 * @author AcoYT
 */
@Mixin(value = Gui.class, priority = 1500)
public abstract class GuiMixin {
    //? if > 1.21.10
    @Shadow protected abstract Gui.ContextualInfo nextContextualInfoState();

    @Inject(method = "extractCameraOverlays", at = @At("HEAD"), cancellable = true)
    private void acornlib$overlays(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().overlays, ci::cancel);
    }

    @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
    private void acornlib$crosshair(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().crosshair, ci::cancel);
    }

    @Inject(method = "extractItemHotbar", at = @At("HEAD"), cancellable = true)
    private void acornlib$hotbar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().hotbar, ci::cancel);
    }

    @Inject(method = "extractArmor", at = @At("HEAD"), cancellable = true)
    private static void acornlib$armor(GuiGraphicsExtractor graphics, Player player, int yLineBase, int numHealthRows, int healthRowHeight, int xLeft, CallbackInfo ci) {
        if (!KEY.get(player).armor) {
            ci.cancel();
        }
    }

    @Inject(method = "extractHearts", at = @At("HEAD"), cancellable = true)
    private void acornlib$health(GuiGraphicsExtractor graphics, Player player, int xLeft, int yLineBase, int healthRowHeight, int heartOffsetIndex, float maxHealth, int currentHealth, int oldHealth, int absorption, boolean blink, CallbackInfo ci) {
        ifTrue(!getData().health, ci::cancel);
    }

    @Inject(method = "extractFood", at = @At("HEAD"), cancellable = true)
    private void acornlib$hunger(GuiGraphicsExtractor graphics, Player player, int yLineBase, int xRight, CallbackInfo ci) {
        ifTrue(!getData().hunger, ci::cancel);
    }

    @WrapOperation(
            //? if > 1.21.10 {
            method = "extractAirBubbles",
            //? } else {
            /*method = "extractPlayerHealth",
            *///? }
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"
            )
    )
    private boolean acornlib$bubbles(Player instance, TagKey<Fluid> tagKey, Operation<Boolean> original) {
        return original.call(instance, tagKey) && KEY.get(instance).bubbles;
    }

    //? if > 1.21.10 {
    @WrapOperation(
            method = "extractHotbarAndDecorations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/contextualbar/ContextualBarRenderer;extractBackground(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"
            )
    )
    private void acornlib$experience1(ContextualBarRenderer instance, GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, Operation<Void> original) {
        if (this.nextContextualInfoState() == Gui.ContextualInfo.EXPERIENCE) {
            ifTrue(getData().experience, () -> original.call(instance, graphics, deltaTracker));
        } else {
            original.call(instance, graphics, deltaTracker);
        }
    }

    @WrapOperation(
            method = "extractHotbarAndDecorations",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/contextualbar/ContextualBarRenderer;extractExperienceLevel(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;I)V"
            )
    )
    private void acornlib$experience2(GuiGraphicsExtractor graphics, Font font, int experienceLevel, Operation<Void> original) {
        ifTrue(getData().experience, () -> original.call(graphics, font, experienceLevel));
    }
    //? } else {
    /*@WrapMethod(method = "isExperienceBarVisible")
    private boolean acornlib$experience1(Operation<Boolean> original) {
        return original.call() && getData().experience;
    }
    *///? }

    @Inject(method = "extractEffects", at = @At("HEAD"), cancellable = true)
    private void acornlib$effects(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().effects, ci::cancel);
    }

    @Inject(
            method = "extractScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void acornlib$sidebar(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().sidebar, ci::cancel);
    }

    @Inject(method = "extractTitle", at = @At("HEAD"), cancellable = true)
    private void acornlib$titles(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().titles, ci::cancel);
    }

    @Inject(method = "extractChat", at = @At("HEAD"), cancellable = true)
    private void acornlib$chat(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().chat, ci::cancel);
    }

    @Inject(method = "extractTabList", at = @At("HEAD"), cancellable = true)
    private void acornlib$players(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        ifTrue(!getData().players, ci::cancel);
    }

    @Inject(method = "extractSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void acornlib$tooltip(GuiGraphicsExtractor graphics, CallbackInfo ci) {
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