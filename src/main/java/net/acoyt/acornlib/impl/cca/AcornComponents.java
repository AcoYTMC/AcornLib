package net.acoyt.acornlib.impl.cca;

import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.minecraft.entity.player.PlayerEntity;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author AcoYT
 */
public class AcornComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AcornData.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AcornData::new);
    }
}
