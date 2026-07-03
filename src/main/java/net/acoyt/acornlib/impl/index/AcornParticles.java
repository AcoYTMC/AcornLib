package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.particles.SpecialParticleData;
import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author AcoYT
 */
public interface AcornParticles {
    ParticleTypeRegistrant PARTICLES = new ParticleTypeRegistrant(AcornLib.MOD_ID);

    SimpleParticleType PURPLE_SWEEP = PARTICLES.register("purple_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType MAGENTA_SWEEP = PARTICLES.register("magenta_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType ALT_GOLD_SWEEP = PARTICLES.register("alt_gold_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType BLACK_SWEEP = PARTICLES.register("black_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType DARK_AQUA_SWEEP = PARTICLES.register("dark_aqua_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType GOLD_SWEEP = PARTICLES.register("gold_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType GRAY_SWEEP = PARTICLES.register("gray_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType LIGHT_GRAY_SWEEP = PARTICLES.register("light_gray_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType GREEN_SWEEP = PARTICLES.register("green_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType RED_SWEEP = PARTICLES.register("red_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType WHITE_SWEEP = PARTICLES.register("white_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType YELLOW_SWEEP = PARTICLES.register("yellow_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType BLUE_SWEEP = PARTICLES.register("blue_sweep", FabricParticleTypes.simple(true));
    SimpleParticleType LIGHT_BLUE_SWEEP = PARTICLES.register("light_blue_sweep", FabricParticleTypes.simple(true));

    ParticleType<SweepParticleEffect> SWEEP_PARTICLE = PARTICLES.register("special_sweep", FabricParticleTypes.complex(true, SweepParticleEffect.CODEC, SweepParticleEffect.STREAM_CODEC));

    @ApiStatus.Experimental
    ParticleType<SpecialParticleData> SPECIAL = PARTICLES.register("special", FabricParticleTypes.complex(true, SpecialParticleData.CODEC, SpecialParticleData.STREAM_CODEC));

    static void init() {}

    @SuppressWarnings("unused")
    interface AdvancedRefs {
        SweepParticleEffect PURPLE_SWEEP = new SweepParticleEffect(0x4d1e78, 0x2c1854);
        SweepParticleEffect MAGENTA_SWEEP = new SweepParticleEffect(0x490d3a, 0x270025);
        SweepParticleEffect ALT_GOLD_SWEEP = new SweepParticleEffect(0xfac15b, 0xe78633);
        SweepParticleEffect BLACK_SWEEP = new SweepParticleEffect(0x121212, 0x000000);
        SweepParticleEffect DARK_AQUA_SWEEP = new SweepParticleEffect(0x37965b, 0x115642);
        SweepParticleEffect GOLD_SWEEP = new SweepParticleEffect(0xef9424, 0xc56d00);
        SweepParticleEffect GRAY_SWEEP = new SweepParticleEffect(0x515151, 0x333333);
        SweepParticleEffect LIGHT_GRAY_SWEEP = new SweepParticleEffect(0xbfbfbf, 0x808080);
        SweepParticleEffect GREEN_SWEEP = new SweepParticleEffect(0x87a363, 0x6a7d51);
        SweepParticleEffect RED_SWEEP = new SweepParticleEffect(0xff0f0f, 0xaa0000);
        SweepParticleEffect WHITE_SWEEP = new SweepParticleEffect(0xffffff, 0xbfbfbf);
        SweepParticleEffect YELLOW_SWEEP = new SweepParticleEffect(0xedd626, 0xebad28);
        SweepParticleEffect BLUE_SWEEP = new SweepParticleEffect(0x1247cf, 0x1a3068);
        SweepParticleEffect LIGHT_BLUE_SWEEP = new SweepParticleEffect(0x1b85b1, 0x1a5168);
    }
}
