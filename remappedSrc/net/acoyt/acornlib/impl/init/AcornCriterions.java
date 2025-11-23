package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornCriterions {
    Map<CriterionTrigger<?>, ResourceLocation> CRITERIONS = new LinkedHashMap<>();

    PlayerTrigger CRITICAL_HIT = create("critical_hit", new PlayerTrigger());
    PlayerTrigger PLAYER_DEATH = create("player_death", new PlayerTrigger());
    PlayerTrigger PLAYER_DAMAGE = create("player_damage", new PlayerTrigger());

    PlayerTrigger HONK = create("honk", new PlayerTrigger());

    static <T extends CriterionTrigger<?>> T create(String name, T criterion) {
        CRITERIONS.put(criterion, AcornLib.id(name));
        return criterion;
    }

    static void init() {
        CRITERIONS.keySet().forEach(criterion -> {
            Registry.register(BuiltInRegistries.TRIGGER_TYPES, CRITERIONS.get(criterion), criterion);
        });
    }
}
