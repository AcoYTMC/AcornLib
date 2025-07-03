package net.acoyt.acornlib.util.supporter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.entity.player.PlayerEntity;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class SupporterUtils {
    private static List<PlayerInfo> cachedPlayers = new ArrayList<>();
    private static long lastFetchTime = 0L;
    private static final long CACHE_DURATION = 300000L;
    public static List<PlayerInfo> list = fetchPlayers();

    public static List<PlayerInfo> fetchPlayers() {
        long now = System.currentTimeMillis();
        if (!cachedPlayers.isEmpty() && now - lastFetchTime < 300000L) {
            return cachedPlayers;
        } else {
            List<PlayerInfo> players = new ArrayList<>();

            try {
                URL url = new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/players.json");
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                if (connection.getResponseCode() != 200) {
                    AcornLib.LOGGER.error("HTTP Error: " + connection.getResponseCode());
                } else {
                    InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                    JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                    if (jsonObject.has("players") && jsonObject.get("players").isJsonArray()) {
                        for(JsonElement element : jsonObject.getAsJsonArray("players")) {
                            JsonObject playerObj = element.getAsJsonObject();
                            String uuid = playerObj.get("uuid").getAsString();
                            String username = playerObj.get("username").getAsString();
                            players.add(new PlayerInfo(uuid, username));
                        }

                        cachedPlayers = players;
                        lastFetchTime = now;
                    } else {
                        AcornLib.LOGGER.error("Error: 'players' field is missing or not an array!");
                    }

                    reader.close();
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return cachedPlayers;
        }
    }

    public static boolean isPlayerSupporter(PlayerEntity player) {
        for(PlayerInfo playerInfo : fetchPlayers()) {
            if (player.getUuidAsString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isUuidFromSupporter(UUID uuid) {
        for(PlayerInfo playerInfo : fetchPlayers()) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPlayerFriend(PlayerEntity player) {
        return AcornLibUtils.ACO_FRIENDS.contains(player.getUuid());
    }

    public static boolean isUuidFromFriend(UUID uuid) {
        return AcornLibUtils.ACO_FRIENDS.contains(uuid);
    }

    public static boolean isSupporterInGeneral(PlayerEntity player) {
        return SupporterUtils.isPlayerSupporter(player) || SupporterUtils.isPlayerFriend(player);
    }

    public record PlayerInfo(String uuid, String name) {}
}
