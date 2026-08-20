package net.acoyt.acornlib.impl.util.supporter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author AcoYT
 */
@SuppressWarnings("deprecation")
public class SupporterUtils {
    private static final List<PlayerInfo> cachedSupporters = new ArrayList<>();
    private static final List<PlayerInfo> cachedFriends = new ArrayList<>();
    private static final List<PlayerInfo> cachedBlacklisted = new ArrayList<>();
    private long lastFetchTime = 0;

    public List<List<PlayerInfo>> fetchPlayers() {
        long now = System.currentTimeMillis();
        long CACHE_DURATION = 5 * 60 * 1000;

        if (now - lastFetchTime < CACHE_DURATION) {
            if (!cachedSupporters.isEmpty() || !cachedFriends.isEmpty() || !cachedBlacklisted.isEmpty()) {
                return List.of(
                        cachedSupporters,
                        cachedFriends,
                        cachedBlacklisted
                );
            }
        }

        List<PlayerInfo> supporters = new ArrayList<>();
        List<PlayerInfo> friends = new ArrayList<>();
        List<PlayerInfo> blacklisted = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://raw.githubusercontent.com/AcoYTMC/Data/main/test.json").openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            if (connection.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream());
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

                boolean failed = false;

                if (containsArray(jsonObject, "supporters")) {
                    JsonArray supporterArray = jsonObject.getAsJsonArray("supporters");
                    for (var element : supporterArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        supporters.add(new PlayerInfo(uuid, username));
                    }

                    cachedSupporters.clear();
                    cachedSupporters.addAll(supporters);
                } else {
                    failed = true;
                }

                if (containsArray(jsonObject, "friends")) {
                    JsonArray friendArray = jsonObject.getAsJsonArray("friends");
                    for (var element : friendArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        friends.add(new PlayerInfo(uuid, username));
                    }

                    cachedFriends.clear();
                    cachedFriends.addAll(friends);
                } else {
                    failed = true;
                }

                if (containsArray(jsonObject, "blacklisted")) {
                    JsonArray blacklistArray = jsonObject.getAsJsonArray("blacklisted");
                    for (var element : blacklistArray) {
                        JsonObject playerObj = element.getAsJsonObject();
                        String uuid = playerObj.get("uuid").getAsString();
                        String username = playerObj.get("username").getAsString();
                        blacklisted.add(new PlayerInfo(uuid, username));
                    }

                    cachedBlacklisted.clear();
                    cachedBlacklisted.addAll(blacklisted);
                } else {
                    failed = true;
                }

                if (failed) {
                    AcornLib.LOGGER.error("Error: one of the following fields are missing, or are not an array: 'supporters' 'friends' 'blacklisted'");
                } else {
                    lastFetchTime = now;
                }

                reader.close();
            } else {
                AcornLib.LOGGER.error("HTTP Error: {}. Keeping cached value {}.", connection.getResponseCode(), List.of(cachedSupporters, cachedFriends, cachedBlacklisted));
            }

            connection.disconnect();
        } catch (IOException e) {
            AcornLib.LOGGER.error(e.getMessage());
        }

        return List.of(
                cachedSupporters,
                cachedFriends,
                cachedBlacklisted
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

    public boolean isBlacklisted(UUID uuid) {
        for (PlayerInfo playerInfo : fetchPlayers().get(2)) {
            if (uuid.toString().equals(playerInfo.uuid())) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unused")
    public static void broadcastNonSupporterError(Player player) {
        //? if > 1.21.11 {
        player.sendOverlayMessage(Component.translatable("tooltip.acornlib.supporter_only"));
        //? } else {
        /*player.displayClientMessage(Component.translatable("tooltip.acornlib.supporter_only"), true);
         *///? }
    }
}
