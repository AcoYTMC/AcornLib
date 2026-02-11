package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

public class AttributeRegistrant extends RegistrantBase<EntityAttribute> {
    public AttributeRegistrant(String modId) {
        super(modId, Registries.ATTRIBUTE);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(attribute -> {
            Identifier id = getId(attribute);
            builder.add(id.withPrefixedPath("attribute.name.").getPath(), formatString(id.getPath()));
        });
    }
}
