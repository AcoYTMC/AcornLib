package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class CreativeModeTabRegistrant extends RegistrantBase<CreativeModeTab> {
    public CreativeModeTabRegistrant(String modId) {
        super(modId, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(group -> {
            ResourceLocation id = getId(group).withPath(st -> "itemGroup." + st);
            builder.add(id.getPath(), formatString(id.getPath()));
        });
    }
}
