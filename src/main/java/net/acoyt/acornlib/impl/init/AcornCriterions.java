package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornCriterions {
    Map<Criterion<?>, Identifier> CRITERIONS = new LinkedHashMap<>();

    TickCriterion CRITICAL_HIT = create("critical_hit", new TickCriterion());
    TickCriterion PLAYER_DEATH = create("player_death", new TickCriterion());
    TickCriterion PLAYER_DAMAGE = create("player_damage", new TickCriterion());

    TickCriterion HONK = create("honk", new TickCriterion());

    static <T extends Criterion<?>> T create(String name, T criterion) {
        CRITERIONS.put(criterion, AcornLib.id(name));
        return criterion;
    }

    static void init() {
        CRITERIONS.keySet().forEach(criterion -> {
            Registry.register(Registries.CRITERION, CRITERIONS.get(criterion), criterion);
        });
    }
}
