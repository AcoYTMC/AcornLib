package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface AcornCriterions {
    TickCriterion CRITICAL_HIT = create("critical_hit", new TickCriterion());
    TickCriterion PLAYER_DEATH = create("player_death", new TickCriterion());
    TickCriterion PLAYER_DAMAGE = create("player_damage", new TickCriterion());

    TickCriterion HONK = create("honk", new TickCriterion());

    static <T extends Criterion<?>> T create(String name, T criterion) {
        return Registry.register(Registries.CRITERION, AcornLib.id(name), criterion);
    }

    static void init() {
        // Criterions are Registered Statically
    }
}
