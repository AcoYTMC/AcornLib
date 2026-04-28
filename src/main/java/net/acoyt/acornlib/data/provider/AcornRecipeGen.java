package net.acoyt.acornlib.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.minecraft.item.Items.*;

/**
 * @author AcoYT
 */
public class AcornRecipeGen extends FabricRecipeProvider {
    public AcornRecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public void generate(RecipeExporter exporter) {
        // Aco Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BROWN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, LIGHT_GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BLUE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, BLACK_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WHITE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, FESTIVE_ACO_PLUSH);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Chem Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, RED_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, LIGHT_GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, BLACK_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WHITE_WOOL);

        // Clown Aco Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BROWN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, LIGHT_GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLUE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, BLACK_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WHITE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, ACO_PLUSH);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH);

        // Festive Aco Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, MAGENTA_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, PURPLE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, LIGHT_GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WHITE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, ACO_PLUSH);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Gnarp Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, LIME_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, GREEN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, YELLOW_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, ORANGE_WOOL);

        // Kio Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WHITE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, LIGHT_GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, BROWN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, GRAY_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, BLACK_WOOL);

        // Mythorical Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, RED_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WHITE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BROWN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, BLACK_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, GRAY_WOOL);

        // Toast Plush
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, BROWN_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, ORANGE_WOOL);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, BLACK_WOOL);
    }
}
