package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;

//? if > 1.21.1 {
import net.minecraft.data.recipes.RecipeProvider;
//? }

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.minecraft.world.item.Items.*;

/**
 * @author AcoYT
 */
public class AcornRecipeGen extends FabricRecipeProvider {
    public AcornRecipeGen(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    //? if > 1.21.1 {
    public RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
        return new RecipeProvider(registries, exporter) {
            public void buildRecipes() {
                // Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, BROWN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, LIGHT_GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, BLUE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, BLACK_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WHITE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, FESTIVE_ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, CLOWN_ACO_PLUSH);

                // Chem Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, RED_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, LIGHT_GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, BLACK_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WHITE_WOOL);

                // Clown Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BROWN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, LIGHT_GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLUE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLACK_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WHITE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH);

                // Festive Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, MAGENTA_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, PURPLE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, LIGHT_GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WHITE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);

                // Gnarp Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, LIME_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, GREEN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, YELLOW_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, ORANGE_WOOL);

                // Kio Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WHITE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, LIGHT_GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, BROWN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, GRAY_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, BLACK_WOOL);

                // Mythorical Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, RED_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WHITE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BROWN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BLACK_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, GRAY_WOOL);

                // Toast Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, BROWN_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, ORANGE_WOOL);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, BLACK_WOOL);
            }
        };
    }
    //? } else {
    /*public void buildRecipes(RecipeOutput exporter) {
        // Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BROWN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, LIGHT_GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BLUE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BLACK_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WHITE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, FESTIVE_ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Chem Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, RED_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, LIGHT_GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, BLACK_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WHITE_WOOL);

        // Clown Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BROWN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, LIGHT_GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLUE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLACK_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WHITE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH);

        // Festive Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, MAGENTA_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, PURPLE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, LIGHT_GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WHITE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Gnarp Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, LIME_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, GREEN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, YELLOW_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, ORANGE_WOOL);

        // Kio Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WHITE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, LIGHT_GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, BROWN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, GRAY_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, BLACK_WOOL);

        // Mythorical Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, RED_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WHITE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BROWN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BLACK_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, GRAY_WOOL);

        // Toast Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, BROWN_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, ORANGE_WOOL);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, BLACK_WOOL);
    }
    *///? }

    public String getName() {
        return AcornLib.MOD_ID + "_recipe";
    }
}
//~ }