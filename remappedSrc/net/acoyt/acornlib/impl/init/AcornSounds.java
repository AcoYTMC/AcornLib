package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public interface AcornSounds {
    Map<SoundEvent, ResourceLocation> SOUND_EVENTS = new LinkedHashMap<>();

    SoundEvent ACO_PLUSH_HONK = create("block.aco_plush.honk");
    SoundEvent FESTIVE_ACO_PLUSH_HONK = create("block.festive_aco_plush.honk");
    SoundEvent CLOWN_ACO_PLUSH_HONK = create("block.clown_aco_plush.honk");
    SoundEvent MYTH_PLUSH_HONK = create("block.myth_plush.honk");
    SoundEvent HOLY_GNARP = create("block.gnarp_plush.honk");
    SoundEvent FOUR_KIO = create("block.kio_plush.honk");
    SoundEvent MREW = create("block.toast_plush.honk");
    SoundEvent GOOBER = create("block.chem_plush.honk");

    SoundEvent CLAIRDELUNE = create("silly.clairdelune");

    static SoundEvent create(String name) {
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(AcornLib.id(name));
        SOUND_EVENTS.put(soundEvent, AcornLib.id(name));
        return soundEvent;
    }

    static void init() {
        SOUND_EVENTS.keySet().forEach((soundEvent) -> {
            Registry.register(BuiltInRegistries.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent);
        });
    }
}
