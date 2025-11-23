package net.acoyt.acornlib.impl.init;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public interface AcornComponents {
    DataComponentType<Unit> TWO_HANDED = create("two_handed", builder -> builder.persistent(Unit.CODEC));
    DataComponentType<Unit> FOLLOWS_CAM = create("follows_cam", builder -> builder.persistent(Unit.CODEC));
    DataComponentType<Unit> SHOW_HAND = create("show_hand", builder -> builder.persistent(Unit.CODEC));
    DataComponentType<Unit> UNDROPPABLE = create("undroppable", builder -> builder.persistent(Unit.CODEC));

    DataComponentType<String> SKIN = create("skin", builder -> builder.persistent(Codec.STRING));
    DataComponentType<ResourceLocation> SECONDARY_MODEL = create("secondary_model", builder -> builder.persistent(ResourceLocation.CODEC));
    DataComponentType<ResourceLocation> TERTIARY_MODEL = create("tertiary_model", builder -> builder.persistent(ResourceLocation.CODEC));
    DataComponentType<SkinLayerComponent> SKIN_LAYER = create("skin_layer", builder -> builder.persistent(SkinLayerComponent.CODEC));
    //ComponentType<SkinOverrideComponent> SKIN_OVERRIDE = create("skin_override", builder -> builder.codec(SkinOverrideComponent.CODEC));

    // Other
    DataComponentType<HitParticleComponent> HIT_PARTICLE = create("hit_particle", builder -> builder.persistent(HitParticleComponent.CODEC));
    DataComponentType<HitSoundComponent> HIT_SOUND = create("hit_sound", builder -> builder.persistent(HitSoundComponent.CODEC));
    DataComponentType<SweepParticleComponent> SWEEP_PARTICLE = create("sweep_particle", builder -> builder.persistent(SweepParticleComponent.CODEC));

    static <T> DataComponentType<T> create(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, AcornLib.id(name), (builderOperator.apply(DataComponentType.builder()).build()));
    }

    static void init() {
        //
    }
}
