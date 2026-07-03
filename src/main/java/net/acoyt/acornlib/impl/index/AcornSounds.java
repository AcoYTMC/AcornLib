package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.SoundEventRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.sounds.SoundEvent;

public interface AcornSounds {
    SoundEventRegistrant SOUNDS = new SoundEventRegistrant(AcornLib.MOD_ID);

    SoundEvent ACO_PLUSH_HONK = SOUNDS.register("block.aco_plush.honk");
    SoundEvent FESTIVE_ACO_PLUSH_HONK = SOUNDS.register("block.festive_aco_plush.honk");
    SoundEvent CLOWN_ACO_PLUSH_HONK = SOUNDS.register("block.clown_aco_plush.honk");
    SoundEvent MYTH_PLUSH_HONK = SOUNDS.register("block.myth_plush.honk");
    SoundEvent HOLY_GNARP = SOUNDS.register("block.gnarp_plush.honk");
    SoundEvent FOUR_KIO = SOUNDS.register("block.kio_plush.honk");
    SoundEvent MREW = SOUNDS.register("block.toast_plush.honk");
    SoundEvent GOOBER = SOUNDS.register("block.chem_plush.honk");

    @SuppressWarnings("unused")
    SoundEvent CLAIRDELUNE = SOUNDS.register("silly.clairdelune");

    static void init() {}
}
