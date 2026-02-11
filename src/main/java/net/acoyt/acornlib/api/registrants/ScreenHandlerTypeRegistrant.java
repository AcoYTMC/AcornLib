package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandlerType;

public class ScreenHandlerTypeRegistrant extends RegistrantBase<ScreenHandlerType<?>> {
    public ScreenHandlerTypeRegistrant(String modId) {
        super(modId, Registries.SCREEN_HANDLER);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // ScreenHandlers don't have lang
    }
}
