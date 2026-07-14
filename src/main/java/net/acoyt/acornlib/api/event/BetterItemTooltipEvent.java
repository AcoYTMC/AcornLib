package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author AcoYT
 */
public interface BetterItemTooltipEvent {
    Event<BetterItemTooltipEvent> EVENT = EventFactory.createArrayBacked(BetterItemTooltipEvent.class, events -> (stack, context, display, lines) -> {
        List<BetterItemTooltipEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(BetterItemTooltipEvent::getPriority));
        for (BetterItemTooltipEvent event : sortedEvents) {
            event.getTooltip(stack, context, display, lines);
        }
    });

    default int getPriority() {
        return 1000;
    }

    //? if > 1.21.1 {
    /*void getTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, Consumer<Component> lines);
    *///? } else {
    void getTooltip(ItemStack stack, Item.TooltipContext tooltipContext, TooltipFlag tooltipFlag, List<Component> lines);
    //? }
}
