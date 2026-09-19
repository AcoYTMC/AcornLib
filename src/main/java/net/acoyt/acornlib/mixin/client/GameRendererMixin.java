package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.ScreenParticlesEvent;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if > 26.1.2
import net.minecraft.client.gui.Hud;

/**
 * @author AcoYT
 */
//~ if > 26.1.2 'GameRenderer' -> 'Gui'
@Mixin(Gui.class)
public abstract class GameRendererMixin {
    @WrapOperation(
            //? if > 26.1.2 {
            method = "extractRenderState",
            //? } else if > 1.21.11 {
            /*method = "extractGui",
            *///? } else {
            /*method = "render",
            *///? }
            at = @At(
                    value = "INVOKE",
                    //~ if > 1.21.11 'render' -> 'extractSavingIndicator'
                    //~ if > 26.1.2 'Gui;' -> 'Hud;'
                    target = "Lnet/minecraft/client/gui/Hud;extractSavingIndicator(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"
            )
    )
    //~ if > 26.1.2 'Gui' -> 'Hud'
    private void acornLib$screenParticlesEvent(Hud instance, GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, Operation<Void> original) {
        original.call(instance, graphics, deltaTracker);
        ScreenParticlesEvent.EVENT.invoker().drawScreenParticles(graphics, deltaTracker);
    }
}
