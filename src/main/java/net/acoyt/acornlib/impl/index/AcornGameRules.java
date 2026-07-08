package net.acoyt.acornlib.impl.index;

//? if > 1.21.10 {
import net.acoyt.acornlib.api.registrants.GameRuleRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
//? } else {
/*import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules.BooleanValue;
import net.minecraft.world.level.GameRules.Category;
import net.minecraft.world.level.GameRules.Key;
*///? }

/**
 * @author AcoYT
 */
public interface AcornGameRules {
    //? if > 1.21.10 {
    GameRuleRegistrant GAME_RULES = new GameRuleRegistrant(AcornLib.MOD_ID);

    GameRule<Boolean> PERSPECTIVE_CHANGING = GAME_RULES.registerBooleanRule(
            "perspective_changing", GameRuleCategory.MISC, true);
    //? } else {
    /*Key<BooleanValue> PERSPECTIVE_CHANGING = GameRuleRegistry.register(
            "perspective_changing", Category.MISC, GameRuleFactory.createBooleanRule(true));
    *///? }

    static void init() {}
}
