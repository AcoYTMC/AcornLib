package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;

/**
 * @author AcoYT
 */
public class MenuTypeRegistrant extends RegistrantBase<MenuType<?>> {
    public MenuTypeRegistrant(String modId) {
        super(modId, BuiltInRegistries.MENU);
    }

    @Deprecated
    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        // ScreenHandlers don't have lang
    }
}
