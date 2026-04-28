package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class EntityTypeRegistrant extends RegistrantBase<EntityType<?>> {
    public EntityTypeRegistrant(String modId) {
        super(modId, Registries.ENTITY_TYPE);
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        return this.register(name, builder.build());
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(entityType -> {
            Identifier id = getId(entityType);
            builder.add(entityType, formatString(id.getPath()));
        });
    }
}
