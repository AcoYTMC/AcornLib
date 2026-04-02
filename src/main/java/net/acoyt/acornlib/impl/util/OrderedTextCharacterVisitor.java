package net.acoyt.acornlib.impl.util;

import net.minecraft.text.*;

public class OrderedTextCharacterVisitor implements CharacterVisitor {
    private final MutableText text = Text.empty();

    public boolean accept(int index, Style style, int codePoint) {
        String car = new String(Character.toChars(codePoint));
        text.append(Text.literal(car).setStyle(style));
        return true;
    }

    public Text getText() {
        return text;
    }

    public static Text get(OrderedText text) {
        OrderedTextCharacterVisitor visitor = new OrderedTextCharacterVisitor();
        text.accept(visitor);
        return visitor.getText();
    }
}
