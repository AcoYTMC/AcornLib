package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.*;

//? if <= 1.21.1 {
/*import net.minecraft.resources.Identifier;
*///? }

/**
 * @author AcoYT
 */
public interface FilterRecipesEvent {
    Event<FilterRecipesEvent> EVENT = EventFactory.createArrayBacked(FilterRecipesEvent.class, events -> (recipes) -> {
        List<FilterRecipesEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(FilterRecipesEvent::getPriority));
        for (FilterRecipesEvent event : sortedEvents) {
            event.filterRecipes(recipes);
        }
    });

    default int getPriority() {
        return 1000;
    }

    //? if > 26.1.2 {
    void filterRecipes(Map<ResourceKey<Recipe<?>>, RecipeHolder<?>> entries);
    //? } else if > 1.21.1 {
    /*void filterRecipes(List<RecipeHolder<?>> entries);
    *///? } else {
    /*void filterRecipes(Map<Identifier, RecipeHolder<?>> entries);
    *///? }
}
