package net.acoyt.acornlib.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.util.Util;
import net.minecraft.component.ComponentType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

public interface AcornDataComponents {
    ComponentType<Unit> TWO_HANDED = create("two_handed", Unit.CODEC, Util.UNIT_PACKET_CODEC);
    ComponentType<Unit> FOLLOWS_CAM = create("follows_cam", Unit.CODEC, Util.UNIT_PACKET_CODEC);
    ComponentType<Unit> SHOW_HAND = create("show_hand", Unit.CODEC, Util.UNIT_PACKET_CODEC);
    ComponentType<Unit> UNDROPPABLE = create("undroppable", Unit.CODEC, Util.UNIT_PACKET_CODEC);

    ComponentType<String> SKIN = create("skin", Codec.STRING, PacketCodecs.STRING);

    // Other
    ComponentType<HitParticleComponent> HIT_PARTICLE = create("hit_particle", HitParticleComponent.CODEC, HitParticleComponent.PACKET_CODEC);
    ComponentType<HitSoundComponent> HIT_SOUND = create("hit_sound", HitSoundComponent.CODEC, HitSoundComponent.PACKET_CODEC);
    ComponentType<SweepParticleComponent> SWEEP_PARTICLE = create("sweep_particle", SweepParticleComponent.CODEC, SweepParticleComponent.PACKET_CODEC);

    static <T> ComponentType<T> create(String id, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, AcornLib.id(id), ComponentType.<T>builder()
                .codec(codec)
                .packetCodec(packetCodec)
                .build());
    }

    static void init() {}
}
