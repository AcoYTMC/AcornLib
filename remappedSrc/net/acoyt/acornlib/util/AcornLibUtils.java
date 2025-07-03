package net.acoyt.acornlib.util;

import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.util.supporter.SupporterUtils;
import net.minecraft.text.Text;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class AcornLibUtils {
    // Birthday
    private static final LocalDate today = LocalDate.now();
    public static final boolean IS_ACO_BIRTHDAY = LocalDate.of(today.getYear(), 9, 12).compareTo(today) * today.compareTo(LocalDate.of(today.getYear(), 9, 12)) >= 0;

    public static final boolean IS_BIRTHDAY = IS_ACO_BIRTHDAY;

    // Aco Utils
    public static int modNameColor = convertToHex(AcornConfig.nameColor);
    public static Text acoName = Text.literal("AcoYT");
    public static UUID acoUuid = UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd");

    // Fried Stuff
    public static int friendColor = 0x13515d;
    public static int supporterColor = 0x8e010c;
    public static int bothColor = 0x9e58f7;

    public static List<UUID> ACO_FRIENDS = List.of(
            UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd"), // AcoYT
            UUID.fromString("dd129c8b-d3c6-4553-92fe-8ba2f0d021c6"), // Mythorical
            UUID.fromString("cf1a7ab0-a06c-4eaa-bc52-f19c01c764b6"), // CoolShardDude
            UUID.fromString("1ac90c17-2ea5-4c84-b1c3-f1456b4185db"), // dumb_genius
            UUID.fromString("d5bb9b9f-d023-4c51-9f84-825b5d0ca88d"), // Skycon77
            UUID.fromString("ba9c2526-bf12-4705-9051-5886e41aad0d"), // The_Hero_Robot
            UUID.fromString("fcbf8158-2bfb-4181-b66c-47c651214da0"), // YAGGl
            UUID.fromString("6035af5b-a74c-4838-8fab-e4c7a76e42d6"), // 4kio
            UUID.fromString("3019035d-6ca1-4f94-82ef-83eb3a6346b9") // Quayru
    );

    public static List<UUID> SCULK_IMMUNE = List.of(
            UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd")
    );

    public static Text stylizeNames(UUID playerUuid, Text text) {
        if (SupporterUtils.isUuidFromFriend(playerUuid) && !SupporterUtils.isUuidFromSupporter(playerUuid)) {
            return text.copy().styled(style -> style.withColor(friendColor));
        } else if (SupporterUtils.isUuidFromFriend(playerUuid) && SupporterUtils.isUuidFromSupporter(playerUuid)) {
            return text.copy().styled(style -> style.withColor(bothColor));
        } else if (!SupporterUtils.isUuidFromFriend(playerUuid) && SupporterUtils.isUuidFromSupporter(playerUuid)) {
            return text.copy().styled(style -> style.withColor(supporterColor));
        } else {
            return text;
        }
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", ""); // Remove # if present
        return Integer.parseInt(hexString, 16); // Convert to an int
    }
}
