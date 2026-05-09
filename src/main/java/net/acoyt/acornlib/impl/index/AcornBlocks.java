package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.block.PlushBlockItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.ItemGroups;

/**
 * @author AcoYT
 */
public interface AcornBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(AcornLib.MOD_ID);

    Block ACO_PLUSH = BLOCKS.registerWithItem("aco_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.CYAN_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0x8d78cd));

    Block FESTIVE_ACO_PLUSH = BLOCKS.registerWithItem("festive_aco_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0xd54dab));

    Block CLOWN_ACO_PLUSH = BLOCKS.registerWithItem("clown_aco_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0x1b84c4));

    Block MYTHORICAL_PLUSH = BLOCKS.registerWithItem("mythorical_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.RED_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, -1));

    Block GNARP_PLUSH = BLOCKS.registerWithItem("gnarp_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.LIME_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, -1));

    Block KIO_PLUSH = BLOCKS.registerWithItem("kio_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0x1d171d));

    Block TOAST_PLUSH = BLOCKS.registerWithItem("toast_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0x852c24));

    Block CHEM_PLUSH = BLOCKS.registerWithItem("chem_plush", PlushBlock::new, AbstractBlock.Settings.copy(Blocks.RED_WOOL)
            .nonOpaque(), (block, settings) -> new PlushBlockItem(block, settings, 0x47091d));

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(AcornBlocks::addFunctionalEntries);
    }

    static void clientInit() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
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
        entries.add(ACO_PLUSH);
        entries.addAfter(ACO_PLUSH, FESTIVE_ACO_PLUSH);
        entries.addAfter(FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);
        entries.addAfter(CLOWN_ACO_PLUSH, MYTHORICAL_PLUSH);
        entries.addAfter(MYTHORICAL_PLUSH, GNARP_PLUSH);
        entries.addAfter(GNARP_PLUSH, KIO_PLUSH);
        entries.addAfter(KIO_PLUSH, TOAST_PLUSH);
        entries.addAfter(TOAST_PLUSH, CHEM_PLUSH);
    }
}
