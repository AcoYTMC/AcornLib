package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricTagProvider' -> 'FabricTagsProvider' {
//~ if > 1.21.11 'BlockTagProvider' -> 'BlockTagsProvider' {
//~ if > 1.21.10 'getOrCreateTagBuilder' -> 'valueLookupBuilder' {
import net.acoyt.acornlib.impl.index.tag.AcornBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornBlockTagGen extends FabricTagsProvider.BlockTagsProvider {
    public AcornBlockTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public void addTags(HolderLookup.Provider registries) {
        this.valueLookupBuilder(AcornBlockTags.PLUSHIES)
                .add(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH)
                .setReplace(false);
    }
}
//~ }
//~ }
//~ }
//~ }