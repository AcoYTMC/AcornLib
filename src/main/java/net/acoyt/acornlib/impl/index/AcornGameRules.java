package net.acoyt.acornlib.impl.index;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules.*;

public interface AcornGameRules {
    Key<BooleanRule> ALLOW_PERSPECTIVE_CHANGING = create("allowPerspectiveChanging", Category.MISC, GameRuleFactory.createBooleanRule(true));

    static <T extends Rule<T>> Key<T> create(String name, Category category, Type<T> value) {
        return GameRuleRegistry.register(name, category, value);
    }

    static void init() {}
}
