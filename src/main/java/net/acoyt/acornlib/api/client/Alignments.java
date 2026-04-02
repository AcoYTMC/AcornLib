package net.acoyt.acornlib.api.client;

import net.acoyt.acornlib.impl.event.client.AlignmentRenderTest;
import net.minecraft.client.gui.DrawContext;

import java.util.function.Function;

/**
 * @author AcoYT
 * @see AlignmentRenderTest for examples on usage
 */
public interface Alignments {
    Alignment TOP_LEFT = new Alignment(context -> new IntPair(0, 0));
    Alignment TOP_CENTER = new Alignment(context -> new IntPair(context.getScaledWindowWidth() / 2, 0));
    Alignment TOP_RIGHT = new Alignment(context -> new IntPair(context.getScaledWindowWidth(), 0));

    Alignment MIDDLE_LEFT = new Alignment(context -> new IntPair(0, context.getScaledWindowHeight() / 2));
    Alignment MIDDLE_CENTER = new Alignment(context -> new IntPair(context.getScaledWindowWidth() / 2, context.getScaledWindowHeight() / 2));
    Alignment MIDDLE_RIGHT = new Alignment(context -> new IntPair(context.getScaledWindowWidth(), context.getScaledWindowHeight() / 2));

    Alignment BOTTOM_LEFT = new Alignment(context -> new IntPair(0, context.getScaledWindowHeight()));
    Alignment BOTTOM_CENTER = new Alignment(context -> new IntPair(context.getScaledWindowWidth() / 2, context.getScaledWindowHeight()));
    Alignment BOTTOM_RIGHT = new Alignment(context -> new IntPair(context.getScaledWindowWidth(), context.getScaledWindowHeight()));

    record Alignment(Function<DrawContext, IntPair> contextFunction) {
        public IntPair apply(DrawContext context) {
            return this.contextFunction.apply(context);
        }

        public IntPair applyOffset(DrawContext context, int xOffset, int yOffset) {
            IntPair original = apply(context);
            return new IntPair(original.x - xOffset, original.y - yOffset);
        }
    }

    record IntPair(int x, int y) {}
}
