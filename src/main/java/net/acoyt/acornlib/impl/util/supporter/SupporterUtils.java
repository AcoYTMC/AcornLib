package net.acoyt.acornlib.impl.util.supporter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.acoyt.acornlib.impl.AcornLib;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class SupporterUtils {
    private static List<PlayerInfo> cachedSupporters = new ArrayList<>();
    private static List<PlayerInfo> cachedFriends = new ArrayList<>();
    private long lastFetchTime = 0;

    public List<List<PlayerInfo>> fetchPlayers() {
        long now = System.currentTimeMillis();
        long CACHE_DURATION = 5 * 60 * 1000;
        if (now - lastFetchTime < CACHE_DURATION) {
            if (!cachedSupporters.isEmpty()) {
                return List.of(
                        cachedSupporters,
                        cachedFriends
                );
            }

            if (!cachedFriends.isEmpty()) {
                return List.of(
                        cachedSupporters,
                        cachedFriends
                );
            }
        }

        List<PlayerInfo> supporters = new ArrayList<>();
        List<PlayerInfo> friends = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/test.json").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                if (containsArray(jsonObject, "supporters") && containsArray(jsonObject, "friends")) {
                    JsonArray supporterArray = jsonObject.getAsJsonArray("supporters");
                    for (var element : supporterArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        supporters.add(new PlayerInfo(uuid, username));
                    }

                    JsonArray friendArray = jsonObject.getAsJsonArray("friends");
                    for (var element : friendArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        friends.add(new PlayerInfo(uuid, username));
                    }

                    cachedSupporters = supporters;
                    cachedFriends = friends;
                    lastFetchTime = now;
                } else {
                    AcornLib.LOGGER.error("Error: one of the following fields are missing, or are not an array: 'supporters' 'friends'");
                }
                reader.close();
            } else {
                AcornLib.LOGGER.error("HTTP Error: {}", connection.getResponseCode());
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return List.of(
                cachedSupporters,
                cachedFriends
        );
    }

    private static boolean containsArray(JsonObject jsonObject, String check) {
        return jsonObject.has(check) && jsonObject.get(check).isJsonArray();
    }

    public boolean isSupporter(UUID uuid) {
        for (PlayerInfo playerInfo : fetchPlayers().getFirst()) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    public boolean isFriend(UUID uuid) {
        for (PlayerInfo playerInfo : fetchPlayers().get(1)) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }
}
