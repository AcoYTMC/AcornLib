package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/// Is know to throw non-fatal errors, but still works fine
@ApiStatus.Experimental
public class EntityTypeRegistrant<T extends Entity> extends RegistrantBase<EntityType<?>> {
    public EntityTypeRegistrant(String modId) {
        super(modId, Registries.ENTITY_TYPE);
    }

    public EntityType<T> register(String name, EntityType.Builder<T> builder) {
        EntityType<T> entityType = builder.build(name);
        return register(name, entityType);
    }

    public EntityType<T> register(String name, EntityType<T> object) {
        this.toRegister.add(object);
        return Registry.register(Registries.ENTITY_TYPE, id(name), object);
    }

    public void registerLang(RegistryWrapper.WrapperLookup registries, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(entityType -> {
            Identifier id = getId(entityType);
            builder.add(entityType, formatString(id.getPath()));
        });
    }
}
