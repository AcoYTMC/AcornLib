package net.acoyt.acornlib.data.provider.resources;

import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Optional;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.acoyt.acornlib.impl.index.AcornItems.ACORN;
import static net.acoyt.acornlib.impl.index.AcornItems.GOLDEN_ACORN;

public class AcornModelGen extends FabricModelProvider {
    public AcornModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        registerPlush(generator, ACO_PLUSH, Identifier.ofVanilla("block/white_wool"), PlushType.DEFAULT);
        registerPlush(generator, CHEM_PLUSH, Identifier.ofVanilla("block/red_wool"), PlushType.DEFAULT);
        registerPlush(generator, CLOWN_ACO_PLUSH, Identifier.ofVanilla("block/white_wool"), PlushType.DEFAULT);
        registerPlush(generator, FESTIVE_ACO_PLUSH, Identifier.ofVanilla("block/white_wool"), PlushType.DEFAULT);
        registerPlush(generator, GNARP_PLUSH, Identifier.ofVanilla("block/lime_wool"), PlushType.DEFAULT);
        //registerPlush(generator, KIO_PLUSH, Identifier.ofVanilla("block/white_wool"), PlushType.WEAPON);
        registerPlush(generator, MYTHORICAL_PLUSH, Identifier.ofVanilla("block/red_wool"), PlushType.DEFAULT);
        //registerPlush(generator, TOAST_PLUSH, Identifier.ofVanilla("block/orange_wool"), PlushType.TAIL);
    }

    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(ACORN, Models.GENERATED);
        generator.register(GOLDEN_ACORN, Models.GENERATED);
    }

    public void registerPlush(BlockStateModelGenerator generator, Block block, Identifier blockForParticle, PlushType type) {
        Model model = getModel(type);
        TexturedModel.Factory factory = TexturedModel.makeFactory(b -> this.plush(b, blockForParticle, type), model);
        Identifier identifier = factory.upload(block, generator.modelCollector);
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, identifier))
                .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
        );
    }

    public TextureMap plush(Block block, Identifier blockForParticle, PlushType type) {
        Identifier identifier = Registries.BLOCK.getId(block).withPrefixedPath("block/");
        TextureMap textureMap = new TextureMap().put(TextureKey.TEXTURE, identifier).put(TextureKey.PARTICLE, blockForParticle);
        if (type == PlushType.TAIL) textureMap.put(TextureKey.of("tail"), identifier.withSuffixedPath("_tail"));
        if (type == PlushType.WEAPON) textureMap.put(TextureKey.of("weapon"), identifier.withSuffixedPath("_weapon"));
        return textureMap;
    }

    public Model getModel(PlushType type) {
        return switch (type) {
            case DEFAULT -> new Model(Optional.of(AcornLib.id("block/plush")), Optional.empty(), TextureKey.TEXTURE, TextureKey.PARTICLE);
            case TAIL -> new Model(Optional.of(AcornLib.id("block/plush_with_tail")), Optional.empty(), TextureKey.TEXTURE, TextureKey.PARTICLE, TextureKey.of("tail"));
            case WEAPON -> new Model(Optional.of(AcornLib.id("block/plush_with_weapon")), Optional.empty(), TextureKey.TEXTURE, TextureKey.PARTICLE, TextureKey.of("weapon"));
        };
    }

    public enum PlushType {
        DEFAULT,
        TAIL,
        WEAPON
    }
}
