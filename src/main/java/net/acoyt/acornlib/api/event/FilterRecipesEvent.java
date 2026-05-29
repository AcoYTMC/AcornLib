package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.util.Identifier;

import java.util.*;

/**
 * @author AcoYT
 */
public interface FilterRecipesEvent {
    Event<FilterRecipesEvent> EVENT = EventFactory.createArrayBacked(FilterRecipesEvent.class, events -> (recipes) -> {
        List<FilterRecipesEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(FilterRecipesEvent::getPriority));
        for (FilterRecipesEvent event : sortedEvents) {
            event.filterRecipesByType(recipes);
        }
    });

    default int getPriority() {
        return 1000;
    }

    void filterRecipesByType(Map<Identifier, RecipeEntry<?>> entries);
}
