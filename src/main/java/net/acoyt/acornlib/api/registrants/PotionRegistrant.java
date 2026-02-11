package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

public class PotionRegistrant extends RegistrantBase<Potion> {
    public PotionRegistrant(String modId) {
        super(modId, Registries.POTION);
    }

    public RegistryEntry<Potion> registerRef(String name, Potion effect) {
        this.toRegister.add(effect);
        return Registry.registerReference(Registries.POTION, id(name), effect);
    }

    public Potion register(String name, Potion object) {
        return registerRef(name, object).value();
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(potion -> {
            Identifier id = getId(potion);
            builder.add(id.withPrefixedPath("item.minecraft.potion.effect.").getPath(), "Potion of " + formatString(id.getPath()));
            builder.add(id.withPrefixedPath("item.minecraft.splash_potion.effect.").getPath(), "Splash Potion of " + formatString(id.getPath()));
            builder.add(id.withPrefixedPath("item.minecraft.lingering_potion.effect.").getPath(), "Lingering Potion of " + formatString(id.getPath()));
        });
    }
}
