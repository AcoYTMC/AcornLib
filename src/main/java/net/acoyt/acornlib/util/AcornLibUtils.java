package net.acoyt.acornlib.util;

import net.acoyt.acornlib.AcornLib;
import net.minecraft.text.Text;

import java.time.LocalDate;
import java.util.UUID;

import static net.acoyt.acornlib.AcornLib.supporters;

@SuppressWarnings("unused")
public class AcornLibUtils {
    // Birthday
    private static final LocalDate today = LocalDate.now();
    public static final boolean IS_ACO_BIRTHDAY = LocalDate.of(today.getYear(), 9, 12).compareTo(today) * today.compareTo(LocalDate.of(today.getYear(), 9, 12)) >= 0;

    public static final boolean IS_BIRTHDAY = IS_ACO_BIRTHDAY;

    // Aco Utils
    public static Text acoName = Text.literal("AcoYT");
    public static UUID acoUuid = UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd");

    // Fried Stuff
    public static int friendColor = 0x13515d;
    public static int supporterColor = 0x8e010c;
    public static int bothColor = 0x9e58f7;

    public static Text stylizeNames(UUID uuid, Text text) {
        boolean bl = AcornLib.isSupporter(uuid);
        int color;

        if (supporters.isFriend(uuid) && !supporters.isSupporter(uuid)) {
            color = friendColor;
        } else if (supporters.isFriend(uuid) && supporters.isSupporter(uuid)) {
            color = bothColor;
        } else if (!supporters.isFriend(uuid) && supporters.isSupporter(uuid)) {
            color = supporterColor;
        } else {
            color = 0xFFFFFF;
        }

        return bl ? text.copy().styled(style -> style.withColor(color)) : text;
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", ""); // Remove # if present
        return Integer.parseInt(hexString, 16); // Convert to an int
    }
}
