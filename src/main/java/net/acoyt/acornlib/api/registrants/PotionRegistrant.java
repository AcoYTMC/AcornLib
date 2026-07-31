package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.alchemy.Potion;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class PotionRegistrant extends RegistrantBase<Potion> {
    public PotionRegistrant(String modId) {
        super(modId, BuiltInRegistries.POTION);
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(potion -> {
            Identifier id = getId(potion);
            builder.add(id.withPrefix("item.minecraft.potion.effect.").getPath(), "Potion of " + formatString(id.getPath()));
            builder.add(id.withPrefix("item.minecraft.splash_potion.effect.").getPath(), "Splash Potion of " + formatString(id.getPath()));
            builder.add(id.withPrefix("item.minecraft.lingering_potion.effect.").getPath(), "Lingering Potion of " + formatString(id.getPath()));
            builder.add(id.withPrefix("item.minecraft.tipped_arrow.effect.").getPath(), "Arrow of " + formatString(id.getPath()));
        });
    }
}
