package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

/**
 * @author AcoYT
 */
public interface CustomRiptideEvent {
    Event<CustomRiptideEvent> EVENT = EventFactory.createArrayBacked(CustomRiptideEvent.class, events -> (player, stack) -> {
        List<CustomRiptideEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CustomRiptideEvent::getPriority));
        for (CustomRiptideEvent event : sortedEvents) {
            Optional<Identifier> overlay = event.getRiptideTexture(player, stack);
            if (overlay.isPresent()) {
                return overlay;
            }
        }
        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<Identifier> getRiptideTexture(Player player, ItemStack stack);
}
