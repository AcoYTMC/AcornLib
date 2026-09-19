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
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTabOutput;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

//? if > 26.1.2 {
import net.acoyt.acornlib.api.block.WrappedBlock;
//? }

/**
 * @author AcoYT
 */
public interface AcornBlocks {
    BlockRegistrant BLOCKS = new BlockRegistrant(AcornLib.MOD_ID);

    //~ if > 26.1.2 'Block ' -> 'WrappedBlock<Block> ' {
    WrappedBlock<Block> ACO_PLUSH = BLOCKS.registerWithItem("aco_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.cyan())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0x8d78cd));

    WrappedBlock<Block> FESTIVE_ACO_PLUSH = BLOCKS.registerWithItem("festive_aco_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.magenta())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0xd54dab));

    WrappedBlock<Block> CLOWN_ACO_PLUSH = BLOCKS.registerWithItem("clown_aco_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.white())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0x1b84c4));

    WrappedBlock<Block> MYTHORICAL_PLUSH = BLOCKS.registerWithItem("mythorical_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.red())
                    .noOcclusion(),
            PlushBlockItem::new);

    WrappedBlock<Block> GNARP_PLUSH = BLOCKS.registerWithItem("gnarp_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.lime())
                    .noOcclusion(),
            PlushBlockItem::new);

    WrappedBlock<Block> KIO_PLUSH = BLOCKS.registerWithItem("kio_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.white())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0x1d171d));

    WrappedBlock<Block> TOAST_PLUSH = BLOCKS.registerWithItem("toast_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.orange())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0x852c24));

    WrappedBlock<Block> CHEM_PLUSH = BLOCKS.registerWithItem("chem_plush", PlushBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.red())
                    .noOcclusion(),
            (block, settings) -> new PlushBlockItem(block, settings, 0x47091d));
    //~ }

    static void init() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(AcornBlocks::addFunctionalEntries);
    }

    private static void addFunctionalEntries(FabricCreativeModeTabOutput entries) {
        //~ if > 26.1.2 ');' -> '.get());' {
        entries.accept(ACO_PLUSH.get());
        entries.accept(FESTIVE_ACO_PLUSH.get());
        entries.accept(CLOWN_ACO_PLUSH.get());
        entries.accept(MYTHORICAL_PLUSH.get());
        entries.accept(GNARP_PLUSH.get());
        entries.accept(KIO_PLUSH.get());
        entries.accept(TOAST_PLUSH.get());
        entries.accept(CHEM_PLUSH.get());
        //~ }
    }
}
//~}
//~}
//~}
//~}
//~}
//~}