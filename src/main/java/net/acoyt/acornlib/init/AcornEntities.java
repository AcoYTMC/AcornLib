package net.acoyt.acornlib.init;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.client.render.pluhs.*;
import net.acoyt.acornlib.plush.ThrownPlushEntity;
import net.acoyt.acornlib.plush.pluhs.*;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AcornEntities {
    public static EntityType<ThrownPlushEntity> THROWN_ACO_PLUSH = create("thrown_aco_plush", createEntityType("thrown_aco_plush", ThrownAcoPlushEntity::new, 0.4f));
    public static EntityType<ThrownPlushEntity> THROWN_FESTIVE_ACO_PLUSH = create("thrown_festive_aco_plush", createEntityType("thrown_festive_aco_plush", ThrownFestiveAcoPlushEntity::new, 0.4f));
    public static EntityType<ThrownPlushEntity> THROWN_CLOWN_ACO_PLUSH = create("thrown_clown_aco_plush", createEntityType("thrown_clown_aco_plush", ThrownClownAcoPlushEntity::new, 0.4f));
    public static EntityType<ThrownPlushEntity> THROWN_MYTHORICAL_PLUSH = create("thrown_mythorical_plush", createEntityType("thrown_mythorical_plush", ThrownMythoricalPlushEntity::new, 0.4f));
    public static EntityType<ThrownPlushEntity> THROWN_GNARP_PLUSH = create("thrown_gnarp_plush", createEntityType("thrown_gnarp_plush", ThrownGnarpPlushEntity::new, 0.4f));
    public static EntityType<ThrownPlushEntity> THROWN_KIO_PLUSH = create("thrown_kio_plush", createEntityType("thrown_kio_plush", ThrownKioPlushEntity::new, 0.4f));

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
        EntityRendererRegistry.register(THROWN_ACO_PLUSH, ThrownAcoPlushEntityRenderer::new);
        EntityRendererRegistry.register(THROWN_FESTIVE_ACO_PLUSH, ThrownFestiveAcoPlushEntityRenderer::new);
        EntityRendererRegistry.register(THROWN_CLOWN_ACO_PLUSH, ThrownClownAcoPlushEntityRenderer::new);
        EntityRendererRegistry.register(THROWN_MYTHORICAL_PLUSH, ThrownMythoricalPlushEntityRenderer::new);
        EntityRendererRegistry.register(THROWN_GNARP_PLUSH, ThrownGnarpPlushEntityRenderer::new);
        EntityRendererRegistry.register(THROWN_KIO_PLUSH, ThrownKioPlushEntityRenderer::new);
    }
}
