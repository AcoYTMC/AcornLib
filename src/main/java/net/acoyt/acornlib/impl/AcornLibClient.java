package net.acoyt.acornlib.impl;

//~ if > 1.21.11 'ParticleFactoryRegistry' -> 'ParticleProviderRegistry' {
import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.api.particles.SpecialParticle;
import net.acoyt.acornlib.impl.client.block.render.PlushBlockEntityRenderer;
import net.acoyt.acornlib.impl.client.particle.SpecialSweepAttackParticle;
import net.acoyt.acornlib.impl.client.particle.SweepAttackParticle;
import net.acoyt.acornlib.impl.event.client.BlacklistEvent;
import net.acoyt.acornlib.impl.event.client.PlushTooltipEvent;
import net.acoyt.acornlib.impl.event.client.SupportersOnlyEvent;
import net.acoyt.acornlib.impl.event.general.PerspectiveEvents;
import net.acoyt.acornlib.impl.index.AcornBlockEntities;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.acoyt.acornlib.impl.index.client.AcornModelLayerLocations;
import net.acoyt.acornlib.impl.networking.AcornNetworking;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

//? if > 1.21.11 {
/*import net.minecraft.client.particle.ParticleRenderType;
 *///? }

/**
 * @author AcoYT
 */
@Environment(EnvType.CLIENT)
public class AcornLibClient implements ClientModInitializer {
    //? if > 1.21.11 {
    /*public static final ParticleRenderType SPECIAL = new ParticleRenderType(AcornLib.id("special").toString());
     *///? }
    public static boolean perspectiveSwitching = true;

    public void onInitializeClient() {
        // Initialization
        BlockEntityRenderers.register(AcornBlockEntities.PLUSH, PlushBlockEntityRenderer::new);

        ParticleFactoryRegistry.getInstance().register(AcornParticles.PURPLE_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.MAGENTA_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.ALT_GOLD_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.BLACK_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.DARK_AQUA_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.GOLD_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.GRAY_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.LIGHT_GRAY_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.GREEN_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.RED_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.WHITE_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.YELLOW_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.BLUE_SWEEP, SweepAttackParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(AcornParticles.LIGHT_BLUE_SWEEP, SweepAttackParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(AcornParticles.SWEEP_PARTICLE, SpecialSweepAttackParticle.Provider::new);

        ParticleFactoryRegistry.getInstance().register(AcornParticles.SPECIAL, SpecialParticle.Provider::new);

        AcornModelLayerLocations.init();

        // Networking
        AcornNetworking.registerClientboundPackets();

        // Events
        ClientTickEvents.END_CLIENT_TICK.register(new SupportersOnlyEvent());
        ClientTickEvents.END_CLIENT_TICK.register(new BlacklistEvent());

        ClientPlayConnectionEvents.JOIN.register(new PerspectiveEvents.ClientJoin());
        ClientPlayConnectionEvents.DISCONNECT.register(new PerspectiveEvents.Disconnect());

        BetterItemTooltipEvent.EVENT.register(new PlushTooltipEvent());
    }
}
//~ }