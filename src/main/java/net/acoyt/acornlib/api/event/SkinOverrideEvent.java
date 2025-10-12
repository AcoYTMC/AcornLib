package net.acoyt.acornlib.api.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

@ApiStatus.Experimental
public interface SkinOverrideEvent {
    Event<SkinOverrideEvent> EVENT = EventFactory.createArrayBacked(SkinOverrideEvent.class, events -> player -> {
        List<SkinOverrideEvent> sortedEvents = new ArrayList<>(Arrays.asList(events));
        sortedEvents.sort(Comparator.comparingInt(SkinOverrideEvent::getPriority));
        for (SkinOverrideEvent event : sortedEvents) {
            Optional<OverrideData> overlay = event.getLayer(player);
            if (overlay.isPresent()) {
                return overlay;
            }
        }

        return Optional.empty();
    });

    default int getPriority() {
        return 1000;
    }

    Optional<OverrideData> getLayer(PlayerEntity player);

    enum Type {
        COMPLETELY("completely"), // The entire body
        NOT_HEAD("not_head"), // Everything but the head
        HEAD("head"), // Just the head
        TORSO("torso"), // The body and arms
        BODY("body"), // Just the chest
        ARMS("arms"), // Just the arms
        LEGS("legs"); // Just the legs

        public final String name;

        Type(String name) {
            this.name = name;
        }

        public static Type fromString(String name) {
            for (Type type : Type.values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }

            return Type.COMPLETELY;
        }
    }

    record OverrideData(Identifier id, boolean slim, Type type) {}
}
