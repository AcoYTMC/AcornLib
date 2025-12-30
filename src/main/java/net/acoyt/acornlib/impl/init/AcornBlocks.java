package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlock;
import net.acoyt.acornlib.impl.block.PlushItem;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.Function;

public interface AcornBlocks {
    Block ACO_PLUSH = createPlush("aco_plush", PlushBlock::new, 0x8D78CD, AbstractBlock.Settings.copy(Blocks.CYAN_WOOL)
            .nonOpaque());

    Block FESTIVE_ACO_PLUSH = createPlush("festive_aco_plush", PlushBlock::new, 0xD54DAB, AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL)
            .nonOpaque());

    Block CLOWN_ACO_PLUSH = createPlush("clown_aco_plush", PlushBlock::new, 0x1B84C4, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
            .nonOpaque());

    Block MYTHORICAL_PLUSH = createPlush("mythorical_plush", PlushBlock::new, -1, AbstractBlock.Settings.copy(Blocks.RED_WOOL)
            .nonOpaque());

    Block GNARP_PLUSH = createPlush("gnarp_plush", PlushBlock::new, -1, AbstractBlock.Settings.copy(Blocks.LIME_WOOL)
            .nonOpaque());

    Block KIO_PLUSH = createPlush("kio_plush", PlushBlock::new, 0x1d171d, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
            .nonOpaque());

    Block TOAST_PLUSH = createPlush("toast_plush", PlushBlock::new, 0x852c24, AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)
            .nonOpaque());

    Block CHEM_PLUSH = createPlush("chem_plush", PlushBlock::new, 0x47091d, AbstractBlock.Settings.copy(Blocks.RED_WOOL)
            .nonOpaque());

    static Block create(String name, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings);
        return Registry.register(Registries.BLOCK, AcornLib.id(name), block);
    }

    static Block createPlush(String name, Function<AbstractBlock.Settings, Block> factory, int descColor, AbstractBlock.Settings settings) {
        Block block = create(name, factory, settings);
        AcornItems.create(name, itemSettings -> new PlushItem(block, itemSettings, descColor), new Item.Settings().equipmentSlot((entity, stack) -> EquipmentSlot.HEAD));
        return block;
    }

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
                TOAST_PLUSH
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
    }
}
