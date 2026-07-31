package net.acoyt.acornlib.impl.index;

//~ if > 1.21.10 'critereon' -> 'criterion' {
import net.acoyt.acornlib.api.registrants.CriterionTriggerRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.advancements.criterion.PlayerTrigger;

/**
 * @author AcoYT
 */
public interface AcornCriteria {
    CriterionTriggerRegistrant CRITERIA = new CriterionTriggerRegistrant(AcornLib.MOD_ID);

    PlayerTrigger CRITICAL_HIT = CRITERIA.register("critical_hit", new PlayerTrigger());
    PlayerTrigger PLAYER_DEATH = CRITERIA.register("player_death", new PlayerTrigger());
    PlayerTrigger PLAYER_DAMAGE = CRITERIA.register("player_damage", new PlayerTrigger());

    PlayerTrigger HONK = CRITERIA.register("honk", new PlayerTrigger());

    static void init() {}
}
//~ }