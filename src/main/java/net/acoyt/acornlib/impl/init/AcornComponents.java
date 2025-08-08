package net.acoyt.acornlib.impl.init;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornComponents {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    ComponentType<Unit> TWO_HANDED = create("two_handed", new ComponentType.Builder<Unit>()
            .codec(Unit.CODEC)
            .build());

    ComponentType<Unit> FOLLOWS_CAM = create("follows_cam", new ComponentType.Builder<Unit>()
            .codec(Unit.CODEC)
            .build());

    ComponentType<Unit> SHOW_HAND = create("show_hand", new ComponentType.Builder<Unit>()
            .codec(Unit.CODEC)
            .build());

    ComponentType<Unit> UNDROPPABLE = create("undroppable", new ComponentType.Builder<Unit>()
            .codec(Unit.CODEC)
            .build());

    ComponentType<String> SKIN = create("skin", new ComponentType.Builder<String>()
            .codec(Codec.STRING)
            .build());

    // Other
    ComponentType<HitParticleComponent> HIT_PARTICLE = create("hit_particle", new ComponentType.Builder<HitParticleComponent>()
            .codec(HitParticleComponent.CODEC)
            .build());

    ComponentType<HitSoundComponent> HIT_SOUND = create("hit_sound", new ComponentType.Builder<HitSoundComponent>()
            .codec(HitSoundComponent.CODEC)
            .build());

    ComponentType<SweepParticleComponent> SWEEP_PARTICLE = create("sweep_particle", new ComponentType.Builder<SweepParticleComponent>()
            .codec(SweepParticleComponent.CODEC)
            .build());

    static <T extends ComponentType<?>> T create(String name, T component) {
        COMPONENTS.put(component, AcornLib.id(name));
        return component;
    }

    static void init() {
        COMPONENTS.keySet().forEach((component) -> {
            Registry.register(Registries.DATA_COMPONENT_TYPE, COMPONENTS.get(component), component);
        });
    }
}
