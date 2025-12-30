package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/// This can be a little buggy with other mods
@ApiStatus.Experimental
public interface CanConsumeItemEvent {
    Event<CanConsumeItemEvent> EVENT = EventFactory.createArrayBacked(CanConsumeItemEvent.class, events -> (player, stack) -> {
        List<CanConsumeItemEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CanConsumeItemEvent::getPriority));
        for (CanConsumeItemEvent event : sortedEvents) {
            return event.canConsume(player, stack);
        }
        return true;
    });

    default int getPriority() {
        return 1000;
    }

    boolean canConsume(PlayerEntity player, ItemStack stack);
}
