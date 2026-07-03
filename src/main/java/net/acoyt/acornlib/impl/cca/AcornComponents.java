package net.acoyt.acornlib.impl.cca;

import net.acoyt.acornlib.impl.cca.entity.AcornData;
import net.minecraft.world.entity.player.Player;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.cca.entity.HappyGhastPlushData;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
*///? }

/**
 * @author AcoYT
 */
public class AcornComponents implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(Player.class, AcornData.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AcornData::new);
        //? if > 1.21.5 {
        /*registry.beginRegistration(HappyGhast.class, HappyGhastPlushData.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(HappyGhastPlushData::new);
        *///? }
    }
}
