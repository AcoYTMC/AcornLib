package net.acoyt.acornlib.impl.index;

//~ if > 1.21.11 'itemgroup.v1.FabricItemGroupEntries' -> 'creativetab.v1.FabricCreativeModeTabOutput' {
//~ if > 1.21.11 'itemgroup.v1.ItemGroupEvents' -> 'creativetab.v1.CreativeModeTabEvents' {
//~ if > 1.21.11 'FabricItemGroupEntries' -> 'FabricCreativeModeTabOutput' {
//~ if > 1.21.11 'ItemGroupEvents' -> 'CreativeModeTabEvents' {
//~ if > 1.21.11 'ModifyEntries' -> 'ModifyOutput' {
//~ if > 1.21.11 'modifyEntriesEvent' -> 'modifyOutputEvent' {
import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.block.PlushBlockItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @author AcoYT
 */
public interface AcornBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(AcornLib.MOD_ID);

    Block ACO_PLUSH = BLOCKS.registerWithItem("aco_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0x8d78cd));

    Block FESTIVE_ACO_PLUSH = BLOCKS.registerWithItem("festive_aco_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0xd54dab));

    Block CLOWN_ACO_PLUSH = BLOCKS.registerWithItem("clown_aco_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0x1b84c4));

    Block MYTHORICAL_PLUSH = BLOCKS.registerWithItem("mythorical_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)
            .noOcclusion(), PlushBlockItem::new);

    Block GNARP_PLUSH = BLOCKS.registerWithItem("gnarp_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL)
            .noOcclusion(), PlushBlockItem::new);

    Block KIO_PLUSH = BLOCKS.registerWithItem("kio_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0x1d171d));

    Block TOAST_PLUSH = BLOCKS.registerWithItem("toast_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0x852c24));

    Block CHEM_PLUSH = BLOCKS.registerWithItem("chem_plush", PlushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL)
            .noOcclusion(), (block, settings) -> new PlushBlockItem(block, settings, 0x47091d));

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(AcornBlocks::addFunctionalEntries);
    }

    private static void addFunctionalEntries(FabricItemGroupEntries entries) {
        entries.accept(ACO_PLUSH);
        entries.accept(FESTIVE_ACO_PLUSH);
        entries.accept(CLOWN_ACO_PLUSH);
        entries.accept(MYTHORICAL_PLUSH);
        entries.accept(GNARP_PLUSH);
        entries.accept(KIO_PLUSH);
        entries.accept(TOAST_PLUSH);
        entries.accept(CHEM_PLUSH);
    }
}
//~}
//~}
//~}
//~}
//~}
//~}