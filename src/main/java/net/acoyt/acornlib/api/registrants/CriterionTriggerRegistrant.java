package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * @author AcoYT
 */
public class CriterionTriggerRegistrant extends RegistrantBase<CriterionTrigger<?>> {
    public CriterionTriggerRegistrant(String modId) {
        super(modId, BuiltInRegistries.TRIGGER_TYPES);
    }

    @Deprecated
    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        // Criterions don't have lang
    }
}
