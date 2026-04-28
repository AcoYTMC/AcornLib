package net.acoyt.acornlib.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornBlockLootTableGen extends FabricBlockLootTableProvider {
    public AcornBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generate() {
        this.addDrop(ACO_PLUSH);
        this.addDrop(CHEM_PLUSH);
        this.addDrop(CLOWN_ACO_PLUSH);
        this.addDrop(FESTIVE_ACO_PLUSH);
        this.addDrop(GNARP_PLUSH);
        this.addDrop(KIO_PLUSH);
        this.addDrop(MYTHORICAL_PLUSH);
        this.addDrop(TOAST_PLUSH);
    }
}
