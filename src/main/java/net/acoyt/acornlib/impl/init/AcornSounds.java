package net.acoyt.acornlib.impl.init;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;

public interface AcornSounds {
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
        return Registry.register(Registries.SOUND_EVENT, AcornLib.id(name), SoundEvent.of(AcornLib.id(name)));
    }

    static void init() {
        // Sound Events are Registered Statically
    }
}
