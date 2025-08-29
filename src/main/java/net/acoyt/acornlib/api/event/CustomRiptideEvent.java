package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.*;

public interface CustomRiptideEvent {
    Event<CustomRiptideEvent> EVENT = EventFactory.createArrayBacked(CustomRiptideEvent.class, events -> player -> {
        List<CustomRiptideEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CustomRiptideEvent::getPriority));
        for (CustomRiptideEvent event : sortedEvents) {
            Optional<Identifier> overlay = event.getRiptideTexture(player);
            if (overlay.isPresent()) {
                return overlay;
            }
        }
        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<Identifier> getRiptideTexture(PlayerEntity player);
}
