package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.builder.GameRuleBuilder;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.Key;

/**
 * @author AcoYT
 */
public interface AcornGameRules {
    GameRuleBuilder GAME_RULES = new GameRuleBuilder();

    Key<BooleanRule> ALLOW_PERSPECTIVE_CHANGING = GAME_RULES.register("allowPerspectiveChanging",
            Category.MISC, GameRuleFactory.createBooleanRule(true)
    );

    static void init() {}
}
