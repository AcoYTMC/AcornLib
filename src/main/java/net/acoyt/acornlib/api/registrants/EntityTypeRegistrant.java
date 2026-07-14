package net.acoyt.acornlib.api.registrants;

import net.acoyt.acornlib.api.template.RegistrantBase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import static net.acoyt.acornlib.api.util.MiscUtils.formatString;

/**
 * @author AcoYT
 */
public class EntityTypeRegistrant extends RegistrantBase<EntityType<?>> {
    public EntityTypeRegistrant(String modId) {
        super(modId, BuiltInRegistries.ENTITY_TYPE);
    }

    public <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> builder) {
        //? if > 1.21.1 {
        /*ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id(name));
        EntityType<T> entityType = builder.build(key);
        *///? } else {
        EntityType<T> entityType = builder.build(name);
        //? }
        return register(name, entityType);
    }

    public void registerLang(HolderLookup.Provider provider, FabricLanguageProvider.TranslationBuilder builder) {
        this.toRegister.forEach(entityType -> {
            ResourceLocation id = getId(entityType);
            builder.add(entityType, formatString(id.getPath()));
        });
    }
}
