package net.acoyt.acornlib.util.supporter;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.util.supporter.impl.PlayerInfo;
import net.acoyt.acornlib.util.supporter.impl.SupporterImpl;
import net.minecraft.entity.player.PlayerEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class FriendUtils extends SupporterImpl {
    public String getId() {
        return AcornLib.MOD_ID;
    }

    public URL getURL() throws MalformedURLException {
        return new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/friends.json");
    }

    public boolean isFriend(PlayerEntity player) {
        for (PlayerInfo playerInfo : this.fetchPlayers()) {
            if (player.getUuidAsString().equals(playerInfo.uuid())) {
                return true;
            }
        }
        return false;
    }

    public boolean isFriend(UUID uuid) {
        for (PlayerInfo playerInfo : this.fetchPlayers()) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }
        return false;
    }
}
