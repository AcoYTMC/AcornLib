package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricBlockLootTableProvider' -> 'FabricBlockLootSubProvider' {
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornBlockLootTableGen extends FabricBlockLootSubProvider {
    public AcornBlockLootTableGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    public void generate() {
        //~ if > 26.1.2 ');' -> '.get());' {
        dropSelf(ACO_PLUSH.get());
        dropSelf(CHEM_PLUSH.get());
        dropSelf(CLOWN_ACO_PLUSH.get());
        dropSelf(FESTIVE_ACO_PLUSH.get());
        dropSelf(GNARP_PLUSH.get());
        dropSelf(KIO_PLUSH.get());
        dropSelf(MYTHORICAL_PLUSH.get());
        dropSelf(TOAST_PLUSH.get());
        //~ }
    }
}
//~ }
//~ }