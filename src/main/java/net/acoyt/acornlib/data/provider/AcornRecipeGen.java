package net.acoyt.acornlib.data.provider;

//~ if > 1.21.11 'FabricDataOutput' -> 'FabricPackOutput' {
import net.acoyt.acornlib.impl.AcornLib;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;

//? if <= 26.1.2 {
/*import net.minecraft.data.recipes.RecipeOutput;
*///? }

//? if > 1.21.1 {
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.crafting.Recipe;
//? }

import java.util.concurrent.CompletableFuture;

import static net.acoyt.acornlib.impl.index.AcornBlocks.*;
import static net.minecraft.world.item.Items.*;

/**
 * @author AcoYT
 */
public class AcornRecipeGen extends FabricRecipeProvider {
    public AcornRecipeGen(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    //public RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
    //    return new RecipeProvider(recipes, advancements) {
    //        public void buildRecipes() {
    //            //
    //        }
    //    };
    //}
    //? if > 1.21.1 {
    //~ if > 26.1.2 'RecipeOutput output' -> 'BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements'
    public RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        //~ if > 26.1.2 'registries, output' -> 'recipes, advancements'
        return new RecipeProvider(recipes, advancements) {
            public void buildRecipes() {
                // Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.brown());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.lightGray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.blue());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.black());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.white());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, FESTIVE_ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, ACO_PLUSH, CLOWN_ACO_PLUSH);

                // Chem Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.red());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.lightGray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.gray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.black());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.white());

                // Clown Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.brown());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.lightGray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.blue());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.black());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.white());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH);

                // Festive Aco Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.magenta());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.purple());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.lightGray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.white());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, ACO_PLUSH);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);

                // Gnarp Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.lime());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.green());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.yellow());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.orange());

                // Kio Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.white());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.lightGray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.brown());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.gray());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.black());

                // Mythorical Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.red());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.white());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.brown());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.black());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.gray());

                // Toast Plush
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.brown());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.orange());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.black());
            }
        };
    }
    //? } else {
    /*public void buildRecipes(RecipeOutput exporter) {
        // Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.brown());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.lightGray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.blue());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.black());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, WOOL.white());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, FESTIVE_ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Chem Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.red());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.lightGray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.gray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.black());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CHEM_PLUSH, WOOL.white());

        // Clown Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.brown());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.lightGray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.blue());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.black());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, WOOL.white());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, CLOWN_ACO_PLUSH, FESTIVE_ACO_PLUSH);

        // Festive Aco Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.magenta());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.purple());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.lightGray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, WOOL.white());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, ACO_PLUSH);
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, FESTIVE_ACO_PLUSH, CLOWN_ACO_PLUSH);

        // Gnarp Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.lime());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.green());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.yellow());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.orange());

        // Kio Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.white());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.lightGray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.brown());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.gray());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, GNARP_PLUSH, WOOL.black());

        // Mythorical Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.red());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.white());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.brown());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.black());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, MYTHORICAL_PLUSH, WOOL.gray());

        // Toast Plush
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.brown());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.orange());
        stonecutterResultFromBase(exporter, RecipeCategory.DECORATIONS, TOAST_PLUSH, WOOL.black());
    }
    *///? }

    public String getName() {
        return AcornLib.MOD_ID + "_recipe";
    }
}
//~ }