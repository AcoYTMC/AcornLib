package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.*;

//? if > 1.21.11 || <= 1.21.1 {
import net.minecraft.resources.Identifier;
//? } else {
/*import net.minecraft.client.renderer.rendertype.RenderType;
*///? }

/**
 * @author AcoYT
 */
public interface CustomRiptideEvent {
    Event<CustomRiptideEvent> EVENT = EventFactory.createArrayBacked(CustomRiptideEvent.class, events -> (player, stack) -> {
        List<CustomRiptideEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(CustomRiptideEvent::getPriority));
        for (CustomRiptideEvent event : sortedEvents) {
            //? if > 1.21.11 || <= 1.21.1 {
            Optional<Identifier> overlay = event.getRiptideTexture(player, stack);
            //? } else if > 1.21.1 {
            /*Optional<RenderType> overlay = event.getRiptideTexture(player, stack);
            *///? }
            if (overlay.isPresent()) {
                return overlay;
            }
        }
        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    //? if > 1.21.11 || <= 1.21.1 {
    Optional<Identifier> getRiptideTexture(Player player, ItemStack stack);
    //? } else if > 1.21.1 {
    /*Optional<RenderType> getRiptideTexture(Player player, ItemStack stack);
     *///? }
}
