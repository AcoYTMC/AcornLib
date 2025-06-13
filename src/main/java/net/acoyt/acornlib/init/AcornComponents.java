package net.acoyt.acornlib.init;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.component.HitParticleComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornComponents {
    Map<ComponentType<?>, Identifier> COMPONENTS = new LinkedHashMap<>();

    ComponentType<Boolean> TWO_HANDED = create("two_handed", new ComponentType.Builder<Boolean>()
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOLEAN)
            .build());

    ComponentType<Boolean> FOLLOWS_CAM = create("follows_cam", new ComponentType.Builder<Boolean>()
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOLEAN)
            .build());

    ComponentType<Boolean> SHOW_HAND = create("show_hand", new ComponentType.Builder<Boolean>()
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOLEAN)
            .build());

    ComponentType<Boolean> UNDROPPABLE = create("undroppable", new ComponentType.Builder<Boolean>()
            .codec(Codec.BOOL)
            .packetCodec(PacketCodecs.BOOLEAN)
            .build());

    ComponentType<String> SKIN = create("skin", new ComponentType.Builder<String>()
            .codec(Codec.STRING)
            .packetCodec(PacketCodecs.STRING)
            .build());

    ComponentType<HitParticleComponent> HIT_PARTICLE = create("hit_particle", new ComponentType.Builder<HitParticleComponent>()
            .codec(HitParticleComponent.CODEC)
            .packetCodec(HitParticleComponent.PACKET_CODEC)
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
