package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/// This can be a little buggy with other mods
@ApiStatus.Experimental
public interface CanEntityHealEvent {
    Event<CanEntityHealEvent> EVENT = EventFactory.createArrayBacked(CanEntityHealEvent.class, events -> living -> {
        List<CanEntityHealEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CanEntityHealEvent::getPriority));
        for (CanEntityHealEvent event : sortedEvents) {
            return event.canEntityHeal(living);
        }
        return true;
    });

    default int getPriority() {
        return 1000;
    }

    boolean canEntityHeal(LivingEntity living);
}
