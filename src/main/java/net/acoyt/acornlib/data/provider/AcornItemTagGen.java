package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.11 'FabricTagProvider' -> 'FabricTagsProvider' {
//~ if > 1.21.11 'ItemTagProvider' -> 'ItemTagsProvider' {
//~ if > 1.21.10 'getOrCreateTagBuilder' -> 'valueLookupBuilder' {
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

/**
 * @author AcoYT
 */
public class AcornItemTagGen extends FabricTagProvider.ItemTagProvider {
    public AcornItemTagGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    public void addTags(HolderLookup.Provider registries) {
        this.getOrCreateTagBuilder(AcornItemTags.PLUSHIES)
                .add(ACO_PLUSH.asItem(), CHEM_PLUSH.asItem(), CLOWN_ACO_PLUSH.asItem(), FESTIVE_ACO_PLUSH.asItem(), GNARP_PLUSH.asItem(), KIO_PLUSH.asItem(), MYTHORICAL_PLUSH.asItem(), TOAST_PLUSH.asItem())
                .setReplace(false);
    }
}
//~ }
//~ }
//~ }
//~ }