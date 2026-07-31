package net.acoyt.acornlib.mixin.event;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.FilterRecipesEvent;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if > 1.21.1 {
import net.minecraft.world.item.crafting.RecipeMap;
import java.util.ArrayList;
import java.util.List;
//? } else {
/*import com.google.common.collect.ImmutableMap;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;
*///? }

/**
 * @author AcoYT
 */
@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    //? if > 1.21.1 {
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
    //? } else {
    /*@WrapOperation(
            method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"
            )
    )
    private ImmutableMap<Identifier, RecipeHolder<?>> acornlib$filterRecipes(ImmutableMap.Builder<Identifier, RecipeHolder<?>> instance, Operation<ImmutableMap<Identifier, RecipeHolder<?>>> original) {
        Map<Identifier, RecipeHolder<?>> filtered = new HashMap<>(instance.build());

        FilterRecipesEvent.EVENT.invoker().filterRecipes(filtered);

        ImmutableMap.Builder<Identifier, RecipeHolder<?>> finalized = ImmutableMap.builder();
        finalized.putAll(filtered);
        return original.call(finalized);
    }
    *///? }
}
