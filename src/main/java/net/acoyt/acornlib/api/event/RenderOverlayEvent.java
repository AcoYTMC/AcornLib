package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.*;

public interface RenderOverlayEvent {
    Event<RenderOverlayEvent> EVENT = EventFactory.createArrayBacked(RenderOverlayEvent.class, events -> player -> {
        List<RenderOverlayEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(RenderOverlayEvent::getPriority));
        for (RenderOverlayEvent event : sortedEvents) {
            Optional<Identifier> overlay = event.getOverlay(player);
            if (overlay.isPresent()) {
                return overlay;
            }
        }
        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<Identifier> getOverlay(PlayerEntity player);
}
