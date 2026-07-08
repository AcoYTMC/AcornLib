package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.DeltaTracker;

//? if > 1.21.11 {
/*import net.minecraft.client.gui.GuiGraphicsExtractor;
 *///? } else {
import net.minecraft.client.gui.GuiGraphics;
//? }

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author AcoYT
 */
public interface ScreenParticlesEvent {
    Event<ScreenParticlesEvent> EVENT = EventFactory.createArrayBacked(ScreenParticlesEvent.class, events -> (graphics, deltaTracker) -> {
        List<ScreenParticlesEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(ScreenParticlesEvent::getPriority));
        for (ScreenParticlesEvent event : sortedEvents) {
            event.drawScreenParticles(graphics, deltaTracker);
        }
    });

    default int getPriority() {
        return 1000;
    }

    //? if > 1.21.11 {
    /*void drawScreenParticles(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker);
     *///? } else {
    void drawScreenParticles(GuiGraphics graphics, DeltaTracker deltaTracker);
    //? }
}
