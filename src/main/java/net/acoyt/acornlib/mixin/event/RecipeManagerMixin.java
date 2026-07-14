package net.acoyt.acornlib.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.FilterRecipesEvent;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AcoYT
 */
@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @WrapOperation(
            method = "prepare(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/RecipeMap;create(Ljava/lang/Iterable;)Lnet/minecraft/world/item/crafting/RecipeMap;"
            )
    )
    private RecipeMap acornlib$filterRecipes(Iterable<RecipeHolder<?>> recipes, Operation<RecipeMap> original) {
        List<RecipeHolder<?>> filtered = new ArrayList<>();
        recipes.forEach(filtered::add);

        FilterRecipesEvent.EVENT.invoker().filterRecipes(filtered);

        return original.call(filtered);
    }
}
