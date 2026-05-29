package net.acoyt.acornlib.mixin.event;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.api.event.FilterRecipesEvent;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.HashMap;
import java.util.Map;

/**
 * @author AcoYT
 */
@Mixin(RecipeManager.class)
public abstract class RecipeManagerMixin {
    @WrapOperation(
            method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"
            )
    )
    private ImmutableMap<Identifier, RecipeEntry<?>> acornlib$filterRecipes(ImmutableMap.Builder<Identifier, RecipeEntry<?>> instance, Operation<ImmutableMap<Identifier, RecipeEntry<?>>> original) {
        Map<Identifier, RecipeEntry<?>> filtered = new HashMap<>(instance.build());

        FilterRecipesEvent.EVENT.invoker().filterRecipesByType(filtered);

        ImmutableMap.Builder<Identifier, RecipeEntry<?>> finalized = ImmutableMap.builder();
        finalized.putAll(filtered);
        return original.call(finalized);
    }
}
