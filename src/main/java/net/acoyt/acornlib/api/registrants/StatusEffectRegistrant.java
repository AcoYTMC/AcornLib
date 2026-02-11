package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

public class StatusEffectRegistrant extends RegistrantBase<StatusEffect> {
    public StatusEffectRegistrant(String modId) {
        super(modId, Registries.STATUS_EFFECT);
    }

    public RegistryEntry<StatusEffect> registerRef(String name, StatusEffect effect) {
        this.toRegister.add(effect);
        return Registry.registerReference(Registries.STATUS_EFFECT, id(name), effect);
    }

    public StatusEffect register(String name, StatusEffect object) {
        return registerRef(name, object).value();
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(effect -> {
            Identifier id = getId(effect);
            builder.add(effect, formatString(id.getPath()));
        });
    }
}
