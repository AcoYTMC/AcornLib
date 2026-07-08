package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class MobEffectRegistrant extends RegistrantBase<MobEffect> {
    public MobEffectRegistrant(String modId) {
        super(modId, BuiltInRegistries.MOB_EFFECT);
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(effect -> {
            Identifier id = getId(effect);
            builder.add(effect, formatString(id.getPath()));
        });
    }
}
