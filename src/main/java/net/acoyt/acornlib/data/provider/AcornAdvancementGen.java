package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.8 'critereon' -> 'criterion' {
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.index.AcornCriteria;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
//? if > 1.21.11
import net.minecraft.world.item.ItemStackTemplate;

//? if > 26.1.2 {
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
//? } else if > 1.21.1 {
/*import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.PlayerTrigger;
*///? } else {
/*import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.PlayerTrigger;
*///? }

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

    public AcornAdvancementGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer) {
        //? if > 26.1.2 {
        AdvancementHolder collection = Advancement.Builder.recipeAdvancement()
                .parent(AcornLib.id("honk"))
                .display(new DisplayInfo(
                        new ItemStackTemplate(GNARP_PLUSH.asItem()),
                        Component.translatable("advancements.acornlib.complete_collection.title"),
                        Component.translatable("advancements.acornlib.complete_collection.description"),
                        Optional.empty(),
                        AdvancementType.GOAL,
                        true,
                        true,
                        false
                )).requirements(AdvancementRequirements.allOf(List.of("plush")))
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("plush", InventoryChangeTrigger.TriggerInstance.hasItems(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH))
                .build(AcornLib.id("complete_collection"));

        consumer.accept(collection);
        entries.put(AcornLib.id("complete_collection"), collection);

        AdvancementHolder honk = Advancement.Builder.recipeAdvancement()
                .parent(Identifier.withDefaultNamespace("husbandry/root"))
                .display(new DisplayInfo(
                        new ItemStackTemplate(ACO_PLUSH.asItem()),
                        Component.translatable("advancements.acornlib.honk.title"),
                        Component.translatable("advancements.acornlib.honk.description"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )).requirements(AdvancementRequirements.allOf(List.of("honk")))
                .requirements(AdvancementRequirements.Strategy.AND)
                .addCriterion("honk", AcornCriteria.HONK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.empty())))
                .build(AcornLib.id("honk"));

        consumer.accept(honk);
        entries.put(AcornLib.id("honk"), honk);
        //? } else {
        /*AdvancementHolder collection = Advancement.Builder.recipeAdvancement()
                .parent(AcornLib.id("honk"))
                .display(
                        GNARP_PLUSH.asItem(),
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
        *///? }
    }
}
//~ }
//~ }