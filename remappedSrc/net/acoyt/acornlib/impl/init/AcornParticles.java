package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.particle.SpecialSweepAttackParticle;
import net.acoyt.acornlib.impl.client.particle.SweepAttackParticle;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public interface AcornParticles {
    SimpleParticleType PURPLE_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType MAGENTA_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType ALT_GOLD_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType BLACK_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType DARK_AQUA_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType GOLD_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType GRAY_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType LIGHT_GRAY_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType GREEN_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType RED_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType WHITE_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType YELLOW_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType BLUE_SWEEP = FabricParticleTypes.simple(true);
    SimpleParticleType LIGHT_BLUE_SWEEP = FabricParticleTypes.simple(true);

    ParticleType<SweepParticleEffect> SWEEP_PARTICLE = FabricParticleTypes.complex(true, SweepParticleEffect.CODEC, SweepParticleEffect.PACKET_CODEC);

    private static void create(String name, ParticleType<?> particle) {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, AcornLib.id(name), particle);
    }

    static void init() {
        create("purple_sweep", PURPLE_SWEEP);
        create("magenta_sweep", MAGENTA_SWEEP);
        create("alt_gold_sweep", ALT_GOLD_SWEEP);
        create("black_sweep", BLACK_SWEEP);
        create("dark_aqua_sweep", DARK_AQUA_SWEEP);
        create("gold_sweep", GOLD_SWEEP);
        create("gray_sweep", GRAY_SWEEP);
        create("light_gray_sweep", LIGHT_GRAY_SWEEP);
        create("green_sweep", GREEN_SWEEP);
        create("red_sweep", RED_SWEEP);
        create("white_sweep", WHITE_SWEEP);
        create("yellow_sweep", YELLOW_SWEEP);
        create("blue_sweep", BLUE_SWEEP);
        create("light_blue_sweep", LIGHT_BLUE_SWEEP);

        create("special_sweep", SWEEP_PARTICLE);
    }

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(PURPLE_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(MAGENTA_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ALT_GOLD_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BLACK_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(DARK_AQUA_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GOLD_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GRAY_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(LIGHT_GRAY_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GREEN_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(RED_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(WHITE_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(YELLOW_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(BLUE_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(LIGHT_BLUE_SWEEP, SweepAttackParticle.Factory::new);

        ParticleFactoryRegistry.getInstance().register(SWEEP_PARTICLE, SpecialSweepAttackParticle.Factory::new);
    }

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
