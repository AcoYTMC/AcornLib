package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricBlockLootTableProvider' -> 'FabricBlockLootSubProvider' {
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornBlockLootTableGen extends FabricBlockLootTableProvider {
    public AcornBlockLootTableGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void generate() {
        dropSelf(ACO_PLUSH);
        dropSelf(CHEM_PLUSH);
        dropSelf(CLOWN_ACO_PLUSH);
        dropSelf(FESTIVE_ACO_PLUSH);
        dropSelf(GNARP_PLUSH);
        dropSelf(KIO_PLUSH);
        dropSelf(MYTHORICAL_PLUSH);
        dropSelf(TOAST_PLUSH);
    }
}
//~ }
//~ }