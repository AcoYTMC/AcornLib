package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.GameRuleRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

/**
 * @author AcoYT
 */
public interface AcornGameRules {
    GameRuleRegistrant GAME_RULES = new GameRuleRegistrant(AcornLib.MOD_ID);

    GameRule<Boolean> PERSPECTIVE_CHANGING = GAME_RULES.registerBooleanRule(
            "perspective_changing", GameRuleCategory.MISC, true
    );

    static void init() {}
}
