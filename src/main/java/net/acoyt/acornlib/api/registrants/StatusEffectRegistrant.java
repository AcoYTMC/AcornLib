package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class StatusEffectRegistrant extends RegistrantBase<StatusEffect> {
    public StatusEffectRegistrant(String modId) {
        super(modId, Registries.STATUS_EFFECT);
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
