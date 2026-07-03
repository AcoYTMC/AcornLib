package net.acoyt.acornlib.data;

import net.acoyt.acornlib.data.provider.*;
import net.acoyt.acornlib.data.provider.lang.AcornLangGen;
import net.acoyt.acornlib.data.provider.lang.AcornLolCatLangGen;
import net.acoyt.acornlib.data.provider.resources.AcornModelGen;
import net.acoyt.acornlib.data.provider.resources.AcornParticleGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AcornDatagen implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(AcornLangGen::new);
        pack.addProvider(AcornLolCatLangGen::new);

        pack.addProvider(AcornModelGen::new);
        pack.addProvider(AcornParticleGen::new);

        pack.addProvider(AcornBlockTagGen::new);
        pack.addProvider(AcornItemTagGen::new);

        pack.addProvider(AcornAdvancementGen::new);
        pack.addProvider(AcornRecipeGen::new);
        pack.addProvider(AcornBlockLootTableGen::new);
    }
}
