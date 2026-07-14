package net.acoyt.acornlib.api.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;

import java.util.*;

/**
 * @author AcoYT
 */
@Environment(EnvType.CLIENT)
public interface PlayerOpacityEvent {
    Event<PlayerOpacityEvent> EVENT = EventFactory.createArrayBacked(PlayerOpacityEvent.class, events -> player -> {
        List<PlayerOpacityEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(PlayerOpacityEvent::getPriority));
        for (PlayerOpacityEvent event : sortedEvents) {
            Optional<Double> overlay = event.getOpacity(player);
            if (overlay.isPresent()) {
                return overlay;
            }
        }

        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<Double> getOpacity(Player player);
}
