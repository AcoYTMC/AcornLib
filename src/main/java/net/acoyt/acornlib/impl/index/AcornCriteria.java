package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.CriterionRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.advancement.criterion.TickCriterion;

/**
 * @author AcoYT
 */
public interface AcornCriteria {
    CriterionRegistrant CRITERIA = new CriterionRegistrant(AcornLib.MOD_ID);

    TickCriterion CRITICAL_HIT = CRITERIA.register("critical_hit", new TickCriterion());
    TickCriterion PLAYER_DEATH = CRITERIA.register("player_death", new TickCriterion());
    TickCriterion PLAYER_DAMAGE = CRITERIA.register("player_damage", new TickCriterion());

    TickCriterion HONK = CRITERIA.register("honk", new TickCriterion());

    static void init() {}
}
