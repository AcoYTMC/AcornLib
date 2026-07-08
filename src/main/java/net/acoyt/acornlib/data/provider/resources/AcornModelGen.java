package net.acoyt.acornlib.data.provider.resources;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
//~ if > 1.21.3 'datagen.v1.provider.FabricModelProvider' -> 'client.datagen.v1.provider.FabricModelProvider' {
//~ if > 1.21.3 'data.models.' -> 'client.data.models.' {
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

import static net.acoyt.acornlib.impl.index.AcornItems.ACORN;
import static net.acoyt.acornlib.impl.index.AcornItems.GOLDEN_ACORN;

/**
 * @author AcoYT
 */
public class AcornModelGen extends FabricModelProvider {
    public AcornModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockModelGenerators generators) {
        //
    }

    public void generateItemModels(ItemModelGenerators generators) {
        generators.generateFlatItem(ACORN, ModelTemplates.FLAT_ITEM);
        generators.generateFlatItem(GOLDEN_ACORN, ModelTemplates.FLAT_ITEM);
    }
}
//~ }
//~ }
//~ }