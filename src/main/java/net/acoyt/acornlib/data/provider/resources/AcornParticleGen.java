package net.acoyt.acornlib.data.provider.resources;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornParticles;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author AcoYT
 */
public class AcornParticleGen implements DataProvider {
    private final PackOutput.PathProvider pathResolver;

    public AcornParticleGen(FabricDataOutput output) {
        this.pathResolver = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "particles");
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(AcornParticles.ALT_GOLD_SWEEP, rangeBetween(AcornLib.id("alt_gold/sweep"), 0, 7));
        consumer.accept(AcornParticles.BLACK_SWEEP, rangeBetween(AcornLib.id("black/sweep"), 0, 7));
        consumer.accept(AcornParticles.BLUE_SWEEP, rangeBetween(AcornLib.id("blue/sweep"), 0, 7));
        consumer.accept(AcornParticles.DARK_AQUA_SWEEP, rangeBetween(AcornLib.id("dark_aqua/sweep"), 0, 7));
        consumer.accept(AcornParticles.GOLD_SWEEP, rangeBetween(AcornLib.id("gold/sweep"), 0, 7));
        consumer.accept(AcornParticles.GRAY_SWEEP, rangeBetween(AcornLib.id("gray/sweep"), 0, 7));
        consumer.accept(AcornParticles.GREEN_SWEEP, rangeBetween(AcornLib.id("green/sweep"), 0, 7));
        consumer.accept(AcornParticles.LIGHT_BLUE_SWEEP, rangeBetween(AcornLib.id("light_blue/sweep"), 0, 7));
        consumer.accept(AcornParticles.LIGHT_GRAY_SWEEP, rangeBetween(AcornLib.id("light_gray/sweep"), 0, 7));
        consumer.accept(AcornParticles.MAGENTA_SWEEP, rangeBetween(AcornLib.id("magenta/sweep"), 0, 7));
        consumer.accept(AcornParticles.PURPLE_SWEEP, rangeBetween(AcornLib.id("purple/sweep"), 0, 7));
        consumer.accept(AcornParticles.RED_SWEEP, rangeBetween(AcornLib.id("red/sweep"), 0, 7));
        consumer.accept(AcornParticles.WHITE_SWEEP, rangeBetween(AcornLib.id("white/sweep"), 0, 7));
        consumer.accept(AcornParticles.YELLOW_SWEEP, rangeBetween(AcornLib.id("yellow/sweep"), 0, 7));

        consumer.accept(AcornParticles.SWEEP_PARTICLE, Arrays.asList(
                AcornLib.id("special/sweep_00"),
                AcornLib.id("special/sweep_01"),
                AcornLib.id("special/sweep_02"),
                AcornLib.id("special/sweep_03"),
                AcornLib.id("special/sweep_04"),
                AcornLib.id("special/sweep_05"),
                AcornLib.id("special/sweep_06"),
                AcornLib.id("special/sweep_07"),
                AcornLib.id("special/shadow/sweep_shadow_00"),
                AcornLib.id("special/shadow/sweep_shadow_01"),
                AcornLib.id("special/shadow/sweep_shadow_02"),
                AcornLib.id("special/shadow/sweep_shadow_03"),
                AcornLib.id("special/shadow/sweep_shadow_04"),
                AcornLib.id("special/shadow/sweep_shadow_05"),
                AcornLib.id("special/shadow/sweep_shadow_06"),
                AcornLib.id("special/shadow/sweep_shadow_07")
        ));
    }

    public Identifier[] rangeBetween(Identifier texture, int minInclusive, int maxInclusive) {
        Identifier[] textures = new Identifier[maxInclusive - minInclusive + 1];
        for(int i = minInclusive; i <= maxInclusive; i++) {
            textures[i] = texture.withSuffix("_0" + i);
        }

        return textures;
    }

    public CompletableFuture<?> run(CachedOutput writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();
        Map<Identifier, List<Identifier>> map = new Object2ObjectOpenHashMap<>();

        this.generate((particleType, textures) ->
                map.put(BuiltInRegistries.PARTICLE_TYPE.getKey(particleType), textures)
        );

        map.forEach((id, textures) -> {
            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            textures.forEach(texture -> jsonArray.add(texture.toString()));
            jsonObject.add("textures", jsonArray);
            futures.add(DataProvider.saveStable(writer, jsonObject, this.pathResolver.json(id)));
        });

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    public String getName() {
        return "Particle";
    }

    public interface ParticleDataConsumer {
        void accept(ParticleType<?> particleType, List<Identifier> textures);

        default void accept(ParticleType<?> particleType, Identifier... textures) {
            this.accept(particleType, List.of(textures));
        }
    }
}
//~ }