package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.api.registrants.BlockEntityTypeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.acoyt.acornlib.impl.client.PlushBlockEntityRenderer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

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
        ALib.plushies.forEach(plushData -> PLUSH.addSupportedBlock(plushData.block()));
    }

    static void clientInit() {
        BlockEntityRendererFactories.register(PLUSH, PlushBlockEntityRenderer::new);
    }
}
