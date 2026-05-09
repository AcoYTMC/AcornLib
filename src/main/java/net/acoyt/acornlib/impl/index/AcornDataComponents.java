package net.acoyt.acornlib.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.ComponentTypeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.util.Util;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Unit;

/**
 * @author AcoYT
 */
public interface AcornDataComponents {
    ComponentTypeRegistrant COMPONENTS = new ComponentTypeRegistrant(AcornLib.MOD_ID);

    ComponentType<Unit> TWO_HANDED = COMPONENTS.register("two_handed",
            Unit.CODEC, Util.UNIT_PACKET_CODEC
    );
    ComponentType<Unit> FOLLOWS_CAM = COMPONENTS.register("follows_cam",
            Unit.CODEC, Util.UNIT_PACKET_CODEC
    );
    ComponentType<Unit> SHOW_HAND = COMPONENTS.register("show_hand",
            Unit.CODEC, Util.UNIT_PACKET_CODEC
    );
    ComponentType<Unit> UNDROPPABLE = COMPONENTS.register("undroppable",
            Unit.CODEC, Util.UNIT_PACKET_CODEC
    );

    ComponentType<String> SKIN = COMPONENTS.register("skin",
            Codec.STRING, PacketCodecs.STRING
    );

    // Other
    ComponentType<HitParticleComponent> HIT_PARTICLE = COMPONENTS.register("hit_particle",
            HitParticleComponent.CODEC, HitParticleComponent.PACKET_CODEC
    );
    ComponentType<HitSoundComponent> HIT_SOUND = COMPONENTS.register("hit_sound",
            HitSoundComponent.CODEC, HitSoundComponent.PACKET_CODEC
    );
    ComponentType<SweepParticleComponent> SWEEP_PARTICLE = COMPONENTS.register("sweep_particle",
            SweepParticleComponent.CODEC, SweepParticleComponent.PACKET_CODEC
    );

    static void init() {}
}
