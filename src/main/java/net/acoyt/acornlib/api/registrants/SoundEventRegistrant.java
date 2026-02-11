package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;

public class SoundEventRegistrant extends RegistrantBase<SoundEvent> {
    public SoundEventRegistrant(String modId) {
        super(modId, Registries.SOUND_EVENT);
    }

    public SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.of(id(name));
        return register(name, soundEvent);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // SoundEvents don't have lang
    }
}
