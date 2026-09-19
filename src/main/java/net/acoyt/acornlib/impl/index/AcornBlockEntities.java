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
            //~ if > 26.1.2 'PLUSH' -> 'PLUSH.get()'
            .addBlocks(ACO_PLUSH.get(), FESTIVE_ACO_PLUSH.get(), CLOWN_ACO_PLUSH.get(), MYTHORICAL_PLUSH.get(), GNARP_PLUSH.get(), KIO_PLUSH.get(), TOAST_PLUSH.get(), CHEM_PLUSH.get())
    );

    static void init() {
        //? if > 1.21.11 {
        ALib.plushies.forEach(plushData -> PLUSH.addValidBlock(plushData.block()));
        //? } else {
        /*ALib.plushies.forEach(plushData -> PLUSH.addSupportedBlock(plushData.block()));
        *///? }
    }
}
