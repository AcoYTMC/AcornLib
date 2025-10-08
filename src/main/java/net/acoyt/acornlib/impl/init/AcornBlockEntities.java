package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.block.PlushBlockEntity;
import net.acoyt.acornlib.impl.client.PlushBlockEntityRenderer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.acoyt.acornlib.impl.init.AcornBlocks.*;

public interface AcornBlockEntities {
    Map<BlockEntityType<?>, Identifier> BLOCK_ENTITIES = new LinkedHashMap<>();

    BlockEntityType<PlushBlockEntity> PLUSH = create("plush", FabricBlockEntityTypeBuilder
            .create(PlushBlockEntity::new)
            .addBlocks(ACO_PLUSH, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH, MYTHORICAL_PLUSH, GNARP_PLUSH, KIO_PLUSH, TOAST_PLUSH, CHEM_PLUSH)
            .build());

    private static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType<T> blockEntity) {
        BLOCK_ENTITIES.put(blockEntity, AcornLib.id(name));
        return blockEntity;
    }

    static void init() {
        BLOCK_ENTITIES.keySet().forEach(blockEntity -> {
            Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITIES.get(blockEntity), blockEntity);
        });

        ALib.plushies.forEach(plushData -> PLUSH.addSupportedBlock(plushData.block()));
    }

    static void clientInit() {
        BlockEntityRendererFactories.register(PLUSH, PlushBlockEntityRenderer::new);
    }
}
