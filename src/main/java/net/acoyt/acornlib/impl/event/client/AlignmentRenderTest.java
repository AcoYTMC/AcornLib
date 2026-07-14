package net.acoyt.acornlib.impl.event.client;

//? if > 1.21.1 {
/*import net.acoyt.acornlib.api.client.Alignments;
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
*///? }

/**
 * @author AcoYT
 * @apiNote Serves only as an example for how to use implements
 */
//? if > 1.21.1 {
/*@SuppressWarnings("unused")
public abstract class AlignmentRenderTest implements HudElement {
    public void render(GuiGraphics context, DeltaTracker tickCounter) {
        // Top
        Alignments.IntPair topLeft = Alignments.TOP_LEFT.applyOffset(context, 0, 0);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), topLeft.x(), topLeft.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair topCenter = Alignments.TOP_CENTER.applyOffset(context, 16, 0);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), topCenter.x(), topCenter.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair topRight = Alignments.TOP_RIGHT.applyOffset(context, 32, 0);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), topRight.x(), topRight.y(), 0, 0, 32, 32, 16, 16);

        // Center
        Alignments.IntPair middleLeft = Alignments.MIDDLE_LEFT.applyOffset(context, 0, 0);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), middleLeft.x(), middleLeft.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair middleCenter = Alignments.MIDDLE_CENTER.applyOffset(context, 16, 16);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), middleCenter.x(), middleCenter.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair middleRight = Alignments.MIDDLE_RIGHT.applyOffset(context, 32, 0);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), middleRight.x(), middleRight.y(), 0, 0, 32, 32, 16, 16);

        // Bottom
        Alignments.IntPair bottomLeft = Alignments.BOTTOM_LEFT.applyOffset(context, 0, 32);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), bottomLeft.x(), bottomLeft.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair bottomCenter = Alignments.BOTTOM_CENTER.applyOffset(context, 16, 32);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), bottomCenter.x(), bottomCenter.y(), 0, 0, 32, 32, 16, 16);

        Alignments.IntPair bottomRight = Alignments.BOTTOM_RIGHT.applyOffset(context, 32, 32);
        context.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("test.png"), bottomRight.x(), bottomRight.y(), 0, 0, 32, 32, 16, 16);
    }
}
*///? }