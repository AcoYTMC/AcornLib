package net.acoyt.acornlib.init;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.plush.ThrownPlushEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AcornEntities {
    public static EntityType<ThrownPlushEntity> THROWN_PLUSH = create("thrown_plush", createEntityType("thrown_plush", ThrownPlushEntity::new, 0.4f));

    private static <T extends Entity> EntityType<T> create(String name, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, AcornLib.id(name), entityType);
    }

    private static <T extends Entity> EntityType<T> createEntityType(String name, EntityType.EntityFactory<T> factory, float size) {
        return EntityType.Builder.create(factory, SpawnGroup.MISC).dimensions(size, size).maxTrackingRange(64).trackingTickInterval(20).build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, AcornLib.id(name)));
    }

    public static void init() {
        //
    }

    public static void clientInit() {
        EntityRendererRegistry.register(THROWN_PLUSH, ThrownPlushEntityRenderer::new);
    }
}
