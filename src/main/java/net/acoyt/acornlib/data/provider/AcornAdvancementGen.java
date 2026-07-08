package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.8 'critereon' -> 'criterion' {
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornCriteria;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
@SuppressWarnings("removal")
public class AcornAdvancementGen extends FabricAdvancementProvider {
    public static final Map<Identifier, AdvancementHolder> entries = new HashMap<>();

    public AcornAdvancementGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder collection = Advancement.Builder.recipeAdvancement()
                .parent(AcornLib.id("honk"))
                .display(
                        GNARP_PLUSH,
                        Component.translatable("advancements.acornlib.complete_collection.title"),
                        Component.translatable("advancements.acornlib.complete_collection.description"),
                        null,
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("plush")))
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("plush", InventoryChangeTrigger.TriggerInstance.hasItems(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH))
                .build(AcornLib.id("complete_collection"));

        consumer.accept(collection);
        entries.put(AcornLib.id("complete_collection"), collection);

        AdvancementHolder honk = Advancement.Builder.recipeAdvancement()
                .parent(Identifier.withDefaultNamespace("husbandry/root"))
                .display(
                        ACO_PLUSH,
                        Component.translatable("advancements.acornlib.honk.title"),
                        Component.translatable("advancements.acornlib.honk.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("honk")))
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("honk", AcornCriteria.HONK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
                .build(AcornLib.id("honk"));

        consumer.accept(honk);
        entries.put(AcornLib.id("honk"), honk);
    }
}
//~ }
//~ }