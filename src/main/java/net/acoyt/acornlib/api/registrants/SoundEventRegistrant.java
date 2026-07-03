package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

/**
 * @author AcoYT
 */
public class SoundEventRegistrant extends RegistrantBase<SoundEvent> {
    public SoundEventRegistrant(String modId) {
        super(modId, BuiltInRegistries.SOUND_EVENT);
    }

    public SoundEvent register(String name) {
        SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(id(name));
        return register(name, soundEvent);
    }

    @Deprecated
    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        // SoundEvents don't have lang
    }
}
