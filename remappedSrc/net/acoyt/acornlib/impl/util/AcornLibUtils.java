package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.network.chat.Component;
import java.time.LocalDate;
import java.util.UUID;

import static net.acoyt.acornlib.impl.AcornLib.supporters;

@SuppressWarnings("unused")
public class AcornLibUtils {
    // Birthday
    private static final LocalDate today = LocalDate.now();
    public static final boolean isAcoBirthday = LocalDate.of(today.getYear(), 9, 12).compareTo(today) * today.compareTo(LocalDate.of(today.getYear(), 9, 12)) >= 0;
    public static final boolean isAprilFools = LocalDate.of(today.getYear(), 4, 1).compareTo(today) * today.compareTo(LocalDate.of(today.getYear(), 4, 1)) >= 0;

    // Aco Utils
    public static Component acoName = Component.literal("AcoYT");
    public static UUID acoUuid = UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd");

    // Fried Stuff
    public static int friendColor = 0x13515d;
    public static int supporterColor = 0x8e010c;
    public static int bothColor = 0x9e58f7;
    public static int blacklistedColor = 0x6e070d;

    public static Component stylizeNames(UUID uuid, Component text) {
        boolean bl = AcornLib.isSupporter(uuid) || AcornLib.isBlacklisted(uuid);
        int color;

        if (supporters.isFriend(uuid) && !supporters.isSupporter(uuid)) {
            color = friendColor;
        } else if (supporters.isFriend(uuid) && supporters.isSupporter(uuid)) {
            color = bothColor;
        } else if (!supporters.isFriend(uuid) && supporters.isSupporter(uuid)) {
            color = supporterColor;
        } else if (supporters.isBlacklisted(uuid)) {
            color = blacklistedColor;
        } else {
            color = 0xFFFFFF;
        }

        return bl ? text.copy().withStyle(style -> style.withColor(color)) : text;
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", ""); // Remove # if present
        return Integer.parseInt(hexString, 16); // Convert to an int
    }
}
