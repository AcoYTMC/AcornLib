package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import java.util.*;

public interface RenderSkinLayerEvent {
    Event<RenderSkinLayerEvent> EVENT = EventFactory.createArrayBacked(RenderSkinLayerEvent.class, events -> player -> {
        List<RenderSkinLayerEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(RenderSkinLayerEvent::getPriority));
        for (RenderSkinLayerEvent event : sortedEvents) {
            Optional<ResourceLocation> overlay = event.getLayer(player);
            if (overlay.isPresent()) {
                return overlay;
            }
        }

        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<ResourceLocation> getLayer(Player player);
}
