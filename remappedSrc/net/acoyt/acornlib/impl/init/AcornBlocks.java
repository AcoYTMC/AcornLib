package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.block.PlushItem;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import java.util.function.Function;

public interface AcornBlocks {
    Block ACO_PLUSH = createPlush("aco_plush", PlushBlock::new, 0x8D78CD, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)
            .noOcclusion());

    Block FESTIVE_ACO_PLUSH = createPlush("festive_aco_plush", PlushBlock::new, 0xD54DAB, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)
            .noOcclusion());

    Block CLOWN_ACO_PLUSH = createPlush("clown_aco_plush", PlushBlock::new, 0x1B84C4, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
            .noOcclusion());

    Block MYTHORICAL_PLUSH = createPlush("mythorical_plush", PlushBlock::new, -1, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)
            .noOcclusion());

    Block GNARP_PLUSH = createPlush("gnarp_plush", PlushBlock::new, -1, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL)
            .noOcclusion());

    Block KIO_PLUSH = createPlush("kio_plush", PlushBlock::new, 0x1d171d, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
            .noOcclusion());

    Block TOAST_PLUSH = createPlush("toast_plush", PlushBlock::new, 0x852c24, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)
            .noOcclusion());

    Block CHEM_PLUSH = createPlush("chem_plush", PlushBlock::new, 0x47091d, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)
            .noOcclusion());

    // Create and Register always
    static Block create(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return Blocks.register(ResourceKey.create(Registries.BLOCK, AcornLib.id(name)), factory, settings);
    }

    // Create and Register if specified mod is loaded or if the current instance is a dev environment
    static Block createCompat(String name, String modId, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return FabricLoader.getInstance().isModLoaded(modId) || FabricLoader.getInstance().isDevelopmentEnvironment() ? create(name, factory, settings) : null;
    }

    // Create and Register with an item, always
    static Block createWithItem(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        Block block = create(name, factory, settings);
        AcornItems.create(name, itemSettings -> new BlockItem(block, itemSettings), new Item.Properties().useBlockDescriptionPrefix());
        return block;
    }

    // Create and Register with an item, if specified mod is loaded or if the current instance is a dev environment
    static Block createCompatWithItem(String name, String modId, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        return FabricLoader.getInstance().isModLoaded(modId) || FabricLoader.getInstance().isDevelopmentEnvironment() ? createWithItem(name, factory, settings) : null;
    }

    // Creates a plushie :3
    static Block createPlush(String name, Function<BlockBehaviour.Properties, Block> factory, int descColor, BlockBehaviour.Properties settings) {
        Block block = create(name, factory, settings);
        AcornItems.create(name, itemSettings -> new PlushItem(block, itemSettings, descColor), new Item.Properties().equippableUnswappable(EquipmentSlot.HEAD).useBlockDescriptionPrefix());
        return block;
    }

    static Block createPlushCompat(String name, String modId, Function<BlockBehaviour.Properties, Block> factory, int descColor, BlockBehaviour.Properties settings) {
        return FabricLoader.getInstance().isModLoaded(modId) || FabricLoader.getInstance().isDevelopmentEnvironment() ? createPlush(name, factory, descColor, settings) : null;
    }

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(AcornBlocks::addFunctionalEntries);
    }

    static void clientInit() {
        BlockRenderLayerMap.putBlocks(
                ChunkSectionLayer.CUTOUT,
                ACO_PLUSH,
                FESTIVE_ACO_PLUSH,
                CLOWN_ACO_PLUSH,
                MYTHORICAL_PLUSH,
                GNARP_PLUSH,
                KIO_PLUSH,
                TOAST_PLUSH,
                CHEM_PLUSH
        );
    }

    private static void addFunctionalEntries(FabricItemGroupEntries entries) {
        entries.accept(ACO_PLUSH);
        entries.addAfter(ACO_PLUSH, FESTIVE_ACO_PLUSH);
        entries.addAfter(FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);
        entries.addAfter(CLOWN_ACO_PLUSH, MYTHORICAL_PLUSH);
        entries.addAfter(MYTHORICAL_PLUSH, GNARP_PLUSH);
        entries.addAfter(GNARP_PLUSH, KIO_PLUSH);
        entries.addAfter(KIO_PLUSH, TOAST_PLUSH);
        entries.addAfter(TOAST_PLUSH, CHEM_PLUSH);
    }
}
