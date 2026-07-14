package net.acoyt.acornlib.api.client;

import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Function;

/**
 * @author AcoYT
 */
public interface Alignments {
    Alignment TOP_LEFT = new Alignment(context -> new IntPair(0, 0));
    //? if > 1.21.11 {
    /*Alignment TOP_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, 0));
    Alignment TOP_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), 0));

    Alignment MIDDLE_LEFT = new Alignment(context -> new IntPair(0, context.guiHeight() / 2));
    Alignment MIDDLE_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, context.guiHeight() / 2));
    Alignment MIDDLE_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), context.guiHeight() / 2));

    Alignment BOTTOM_LEFT = new Alignment(context -> new IntPair(0, context.guiHeight()));
    Alignment BOTTOM_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, context.guiHeight()));
    Alignment BOTTOM_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), context.guiHeight()));

    record Alignment(Function<GuiGraphics, IntPair> contextFunction) {
        public IntPair apply(GuiGraphics context) {
            return this.contextFunction.apply(context);
        }

        public IntPair applyOffset(GuiGraphics context, int xOffset, int yOffset) {
            IntPair original = apply(context);
            return new IntPair(original.x - xOffset, original.y - yOffset);
        }
    }
    *///? } else {
    Alignment TOP_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, 0));
    Alignment TOP_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), 0));

    Alignment MIDDLE_LEFT = new Alignment(context -> new IntPair(0, context.guiHeight() / 2));
    Alignment MIDDLE_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, context.guiHeight() / 2));
    Alignment MIDDLE_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), context.guiHeight() / 2));

    Alignment BOTTOM_LEFT = new Alignment(context -> new IntPair(0, context.guiHeight()));
    Alignment BOTTOM_CENTER = new Alignment(context -> new IntPair(context.guiWidth() / 2, context.guiHeight()));
    Alignment BOTTOM_RIGHT = new Alignment(context -> new IntPair(context.guiWidth(), context.guiHeight()));

    record Alignment(Function<GuiGraphics, IntPair> contextFunction) {
        public IntPair apply(GuiGraphics context) {
            return this.contextFunction.apply(context);
        }

        public IntPair applyOffset(GuiGraphics context, int xOffset, int yOffset) {
            IntPair original = apply(context);
            return new IntPair(original.x - xOffset, original.y - yOffset);
        }
    }
    //? }

    record IntPair(int x, int y) {}
}
