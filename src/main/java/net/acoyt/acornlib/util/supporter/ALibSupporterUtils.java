package net.acoyt.acornlib.util.supporter;

import com.nitron.nitrogen.util.SupporterUtils;
import com.nitron.nitrogen.util.interfaces.PlayerInfo;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public final class ALibSupporterUtils extends SupporterUtils {
    public String getId() {
        return AcornLib.MOD_ID;
    }

    @SuppressWarnings("deprecation")
    public URL getURL() throws MalformedURLException {
        return new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/players.json");
    }

    public boolean requiredSupporter() {
        return ALib.getSupporterRequired();
    }

    public boolean isPlayerSupporter(PlayerEntity player) {
        for(PlayerInfo playerInfo : fetchPlayers()) {
            if (player.getUuidAsString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    public boolean isUuidFromSupporter(UUID uuid) {
        for(PlayerInfo playerInfo : fetchPlayers()) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    public boolean isPlayerFriend(PlayerEntity player) {
        return AcornLibUtils.ACO_FRIENDS.contains(player.getUuid());
    }

    public boolean isUuidFromFriend(UUID uuid) {
        return AcornLibUtils.ACO_FRIENDS.contains(uuid);
    }
}
