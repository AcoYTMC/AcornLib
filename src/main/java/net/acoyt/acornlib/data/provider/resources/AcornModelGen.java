package net.acoyt.acornlib.data.provider.resources;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.3 'datagen.v1.provider.FabricModelProvider' -> 'client.datagen.v1.provider.FabricModelProvider' {
//~ if > 1.21.3 'data.models.' -> 'client.data.models.' {

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;

import java.util.Optional;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.acoyt.acornlib.impl.index.AcornItems.ACORN;
import static net.acoyt.acornlib.impl.index.AcornItems.GOLDEN_ACORN;
import static net.minecraft.client.data.models.model.TextureSlot.PARTICLE;
import static net.minecraft.client.data.models.model.TextureSlot.TEXTURE;

//? if > 1.21.11 {
import net.minecraft.client.resources.model.sprite.Material;
//? }

//? if > 1.21.1 {
//? } else {
/*import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
*///? }

/**
 * @author AcoYT
 */
public class AcornModelGen extends FabricModelProvider {
    public static final TextureSlot TAIL = TextureSlot.create("tail");
    public static final TextureSlot WEAPON = TextureSlot.create("weapon");

    public static final ModelTemplate PLUSH = new ModelTemplate(
            Optional.of(AcornLib.id("block/plush")),
            Optional.empty(),
            TEXTURE, PARTICLE
    );

    public static final ModelTemplate PLUSH_WITH_TAIL = new ModelTemplate(
            Optional.of(AcornLib.id("block/plush_with_tail")),
            Optional.empty(),
            TEXTURE, PARTICLE, TAIL
    );

    public static final ModelTemplate PLUSH_WITH_WEAPON = new ModelTemplate(
            Optional.of(AcornLib.id("block/plush_with_weapon")),
            Optional.empty(),
            TEXTURE, PARTICLE, WEAPON
    );

    public AcornModelGen(FabricPackOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockModelGenerators generators) {
        registerPlush(generators,
                ACO_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/white_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                CHEM_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/red_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                CLOWN_ACO_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/white_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                FESTIVE_ACO_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/white_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                GNARP_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/lime_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                KIO_PLUSH, PLUSH_WITH_WEAPON,
                Identifier.withDefaultNamespace("block/white_wool"),
                TEXTURE, PARTICLE, WEAPON
        );
        registerPlush(generators,
                MYTHORICAL_PLUSH, PLUSH,
                Identifier.withDefaultNamespace("block/red_wool"),
                TEXTURE, PARTICLE
        );
        registerPlush(generators,
                TOAST_PLUSH, PLUSH_WITH_TAIL,
                Identifier.withDefaultNamespace("block/orange_wool"),
                TEXTURE, PARTICLE, TAIL
        );
    }

    public void generateItemModels(ItemModelGenerators generators) {
        generators.generateFlatItem(ACORN, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(GOLDEN_ACORN, ModelTemplates.FLAT_ITEM);
    }

    public void registerPlush(BlockModelGenerators generators, Block block, ModelTemplate model, Identifier particle, TextureSlot... slots) {
        Identifier blockId = ModelLocationUtils.getModelLocation(block);

        TextureMapping mapping = new TextureMapping();
        for (TextureSlot slot : slots) {
            Identifier slotId = slot == PARTICLE && particle != null ? particle : blockId.withSuffix(slot == TAIL || slot == WEAPON ? "_" + slot.getId() : "");
            //? if > 1.21.11 {
            mapping.put(slot, new Material(slotId));
            //? } else {
            /*mapping.put(slot, slotId);
            *///? }
        }

        TexturedModel.Provider provider = TexturedModel.createDefault(block2 -> mapping, model);
        //? if > 1.21.1 {
        generators.createHorizontallyRotatedBlock(block, provider);
        //? } else {
        /*Identifier modelId = provider.create(block, generators.modelOutput);
        generators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL, modelId))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
        *///? }
    }
}
//~ }
//~ }
//~ }