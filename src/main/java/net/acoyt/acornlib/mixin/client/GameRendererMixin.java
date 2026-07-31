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

/**
 * @author AcoYT
 */
@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @WrapOperation(
            //~ if > 1.21.11 'render' -> 'extractGui'
            method = "extractGui",
            at = @At(
                    value = "INVOKE",
                    //~ if > 1.21.11 'render' -> 'extractSavingIndicator'
                    target = "Lnet/minecraft/client/gui/Gui;extractSavingIndicator(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"
            )
    )
    private void acornLib$screenParticlesEvent(Gui instance, GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, Operation<Void> original) {
        original.call(instance, graphics, deltaTracker);
        ScreenParticlesEvent.EVENT.invoker().drawScreenParticles(graphics, deltaTracker);
    }
}
