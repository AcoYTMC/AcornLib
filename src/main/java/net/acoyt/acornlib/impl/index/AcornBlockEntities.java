package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
@SuppressWarnings("deprecation")
public interface AcornBlockEntities {
    BlockEntityTypeRegistrant BLOCK_ENTITIES = new BlockEntityTypeRegistrant(AcornLib.MOD_ID);

    BlockEntityType<PlushBlockEntity> PLUSH = BLOCK_ENTITIES.register("plush", FabricBlockEntityTypeBuilder
            .create(PlushBlockEntity::new)
            .addBlocks(ACO_PLUSH, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH, MYTHORICAL_PLUSH, GNARP_PLUSH, KIO_PLUSH, TOAST_PLUSH, CHEM_PLUSH)
    );

    static void init() {
        //? if > 1.21.1 {
        /*ALib.plushies.forEach(plushData -> PLUSH.addValidBlock(plushData.block()));
        *///? } else {
        ALib.plushies.forEach(plushData -> PLUSH.addSupportedBlock(plushData.block()));
        //? }
    }
}
