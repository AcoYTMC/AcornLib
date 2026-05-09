package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

/**
 * @author AcoYT
 */
public class CriterionRegistrant extends RegistrantBase<Criterion<?>> {
    public CriterionRegistrant(String modId) {
        super(modId, Registries.CRITERION);
    }

    @Deprecated
    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        // Criterions don't have lang
    }
}
