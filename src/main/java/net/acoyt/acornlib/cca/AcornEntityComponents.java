package net.acoyt.acornlib.cca;

import net.acoyt.acornlib.AcornLib;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

public class AcornEntityComponents implements EntityComponentInitializer {
    public static final ComponentKey<HoldingComponent> HOLDING = ComponentRegistry.getOrCreate(AcornLib.id("holding"), HoldingComponent.class);

    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, HOLDING).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(HoldingComponent::new);
    }
}
