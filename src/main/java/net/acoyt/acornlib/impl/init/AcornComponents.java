package net.acoyt.acornlib.impl.init;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public interface AcornComponents {
    ComponentType<Unit> TWO_HANDED = create("two_handed", builder -> builder.codec(Unit.CODEC));
    ComponentType<Unit> FOLLOWS_CAM = create("follows_cam", builder -> builder.codec(Unit.CODEC));
    ComponentType<Unit> SHOW_HAND = create("show_hand", builder -> builder.codec(Unit.CODEC));
    ComponentType<Unit> UNDROPPABLE = create("undroppable", builder -> builder.codec(Unit.CODEC));

    ComponentType<String> SKIN = create("skin", builder -> builder.codec(Codec.STRING));
    ComponentType<Identifier> SECONDARY_MODEL = create("secondary_model", builder -> builder.codec(Identifier.CODEC));
    ComponentType<Identifier> TERTIARY_MODEL = create("tertiary_model", builder -> builder.codec(Identifier.CODEC));
    ComponentType<Identifier> SKIN_LAYER = create("skin_layer", builder -> builder.codec(Identifier.CODEC));

    // Other
    ComponentType<HitParticleComponent> HIT_PARTICLE = create("hit_particle", builder -> builder.codec(HitParticleComponent.CODEC));
    ComponentType<HitSoundComponent> HIT_SOUND = create("hit_sound", builder -> builder.codec(HitSoundComponent.CODEC));
    ComponentType<SweepParticleComponent> SWEEP_PARTICLE = create("sweep_particle", builder -> builder.codec(SweepParticleComponent.CODEC));

    static <T> ComponentType<T> create(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, AcornLib.id(name), (builderOperator.apply(ComponentType.builder()).build()));
    }

    static void init() {
        //
    }
}
