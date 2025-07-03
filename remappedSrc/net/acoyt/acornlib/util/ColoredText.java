package net.acoyt.acornlib.util;

import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.List;

@SuppressWarnings("unused")
public class ColoredText {
    /**
     * Takes a text and returns the same text but with the given int color.
     */
    public static Text withColor(Text text, int color) {
        Style style = text.getStyle().withColor(color);
        List<Text> styled = text.getWithStyle(style);
        if (styled.size() > 0) {
            return styled.get(0);
        }
        return text;
    }
}