package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;

import static net.acoyt.acornlib.api.util.MiscUtils.formatAfter;
import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class AttributeRegistrant extends RegistrantBase<Attribute> {
    public AttributeRegistrant(String modId) {
        super(modId, BuiltInRegistries.ATTRIBUTE);
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(attribute -> {
            Identifier id = getId(attribute);
            builder.add(id.withPrefix("attribute.name.").getPath(), formatString(formatAfter(id.getPath(), '.')));
        });
    }
}
