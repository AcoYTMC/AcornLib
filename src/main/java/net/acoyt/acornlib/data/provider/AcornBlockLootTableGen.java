package net.acoyt.acornlib.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

public class AcornBlockLootTableGen extends FabricBlockLootTableProvider {
    public AcornBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        addDrop(ACO_PLUSH);
        addDrop(CHEM_PLUSH);
        addDrop(CLOWN_ACO_PLUSH);
        addDrop(FESTIVE_ACO_PLUSH);
        addDrop(GNARP_PLUSH);
        addDrop(KIO_PLUSH);
        addDrop(MYTHORICAL_PLUSH);
        addDrop(TOAST_PLUSH);
    }
}
