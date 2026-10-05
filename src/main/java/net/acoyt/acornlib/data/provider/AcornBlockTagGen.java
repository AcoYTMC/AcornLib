package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricTagProvider' -> 'FabricTagsProvider' {
//~ if > 1.21.11 'BlockTagProvider' -> 'BlockTagsProvider' {
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
        //? if > 26.1.2 {
        this.builder(AcornBlockTags.PLUSHIES)
                .add(ACO_PLUSH.itemId(), CHEM_PLUSH.itemId(), CLOWN_ACO_PLUSH.itemId(), FESTIVE_ACO_PLUSH.itemId(), GNARP_PLUSH.itemId(), KIO_PLUSH.itemId(), MYTHORICAL_PLUSH.itemId(), TOAST_PLUSH.itemId())
                .setReplace(false);
        //? } else if > 1.21.10 {
        /*this.valueLookupBuilder(AcornBlockTags.PLUSHIES)
                .add(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH)
                .setReplace(false);
        *///? } else {
        /*this.getOrCreateTagBuilder(AcornBlockTags.PLUSHIES)
                .add(ACO_PLUSH, CHEM_PLUSH, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH, GNARP_PLUSH, KIO_PLUSH, MYTHORICAL_PLUSH, TOAST_PLUSH)
                .setReplace(false);
        *///? }
    }
}
//~ }
//~ }
//~ }