package net.acoyt.acornlib.init;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.component.HitParticleComponent;
import net.acoyt.acornlib.component.HitSoundComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornComponents {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    ComponentType<Byte> TWO_HANDED = create("two_handed", new ComponentType.Builder<Byte>()
            .codec(Codec.BYTE)
            .build());

    ComponentType<Byte> FOLLOWS_CAM = create("follows_cam", new ComponentType.Builder<Byte>()
            .codec(Codec.BYTE)
            .build());

    ComponentType<Byte> SHOW_HAND = create("show_hand", new ComponentType.Builder<Byte>()
            .codec(Codec.BYTE)
            .build());

    ComponentType<Byte> UNDROPPABLE = create("undroppable", new ComponentType.Builder<Byte>()
            .codec(Codec.BYTE)
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
