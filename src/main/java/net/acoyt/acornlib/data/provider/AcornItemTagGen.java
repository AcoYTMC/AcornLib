package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricTagProvider' -> 'FabricTagsProvider' {
//~ if > 1.21.11 'ItemTagProvider' -> 'ItemTagsProvider' {
//~ if > 1.21.10 'getOrCreateTagBuilder' -> 'valueLookupBuilder' {
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornItemTagGen extends FabricTagsProvider.ItemTagsProvider {
    public AcornItemTagGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    public void addTags(HolderLookup.Provider registries) {
        //~ if > 26.1.2 'valueLookupBuilder' -> 'builder'
        this.builder(AcornItemTags.PLUSHIES)
                //~ if > 26.1.2 '.asItem()' -> '.itemId()'
                .add(ACO_PLUSH.itemId(), CHEM_PLUSH.itemId(), CLOWN_ACO_PLUSH.itemId(), FESTIVE_ACO_PLUSH.itemId(), GNARP_PLUSH.itemId(), KIO_PLUSH.itemId(), MYTHORICAL_PLUSH.itemId(), TOAST_PLUSH.itemId())
                .setReplace(false);
    }
}
//~ }
//~ }
//~ }
//~ }