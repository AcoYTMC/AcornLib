package net.acoyt.acornlib.data.provider;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornCriterions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRequirements;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

public class AcornAdvancementGen extends FabricAdvancementProvider {
    public static final Map<Identifier, AdvancementEntry> entries = new HashMap<>();

    public AcornAdvancementGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup registries, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry collection = Advancement.Builder.createUntelemetered()
                .display(
                        GNARP_PLUSH,
                        Text.translatable("advancements.acornlib.complete_collection.title"),
                        Text.translatable("advancements.acornlib.complete_collection.description"),
                        null,
                        AdvancementFrame.GOAL,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("plush")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("plush", InventoryChangedCriterion.Conditions.items(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH))
                .build(AcornLib.id("complete_collection"));

        consumer.accept(collection);
        entries.put(AcornLib.id("complete_collection"), collection);

        AdvancementEntry honk = Advancement.Builder.createUntelemetered()
                .display(
                        ACO_PLUSH,
                        Text.translatable("advancements.acornlib.honk.title"),
                        Text.translatable("advancements.acornlib.honk.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("honk")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("honk", AcornCriterions.HONK.create(new TickCriterion.Conditions(Optional.empty())))
                .build(AcornLib.id("honk"));

        consumer.accept(honk);
        entries.put(AcornLib.id("honk"), honk);
    }
}
