package net.acoyt.acornlib.data.provider;

import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;

public class AcornItemTagGen extends FabricTagProvider.ItemTagProvider {
    public AcornItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public void configure(RegistryWrapper.WrapperLookup registries) {
        this.getOrCreateTagBuilder(AcornItemTags.PLUSHIES)
                .add(ACO_PLUSH.asItem(), CHEM_PLUSH.asItem(), CLOWN_ACO_PLUSH.asItem(), FESTIVE_ACO_PLUSH.asItem(), GNARP_PLUSH.asItem(), KIO_PLUSH.asItem(), MYTHORICAL_PLUSH.asItem(), TOAST_PLUSH.asItem())
                .setReplace(false);
    }
}
