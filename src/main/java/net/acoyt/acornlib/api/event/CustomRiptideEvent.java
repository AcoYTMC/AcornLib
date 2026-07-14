package net.acoyt.acornlib.api.event;

//~ if > 1.21.11 '<RenderType' -> '<Identifier' {
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

//? if > 1.21.11 {
/*import net.minecraft.resources.ResourceLocation;
*///? } else {
import net.minecraft.client.renderer.rendertype.RenderType;
//? }

/**
 * @author AcoYT
 */
public interface CustomRiptideEvent {
    Event<CustomRiptideEvent> EVENT = EventFactory.createArrayBacked(CustomRiptideEvent.class, events -> (player, stack) -> {
        List<CustomRiptideEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CustomRiptideEvent::getPriority));
        for (CustomRiptideEvent event : sortedEvents) {
            Optional<RenderType> overlay = event.getRiptideTexture(player, stack);
            if (overlay.isPresent()) {
                return overlay;
            }
        }
        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<RenderType> getRiptideTexture(Player player, ItemStack stack);
}
//~ }