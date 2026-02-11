package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

public class FluidRegistrant extends RegistrantBase<Fluid> {
    public FluidRegistrant(String modId) {
        super(modId, Registries.FLUID);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // Fluids don't have lang
    }
}
