package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    void filterRecipes(List<RecipeHolder<?>> entries);
}
