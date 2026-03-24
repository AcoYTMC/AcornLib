package net.acoyt.acornlib.impl.util;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Ownable;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.text.Text;
import net.minecraft.util.Unit;

import java.time.LocalDate;
import java.util.UUID;

import static net.acoyt.acornlib.impl.AcornLib.supporters;

@SuppressWarnings("unused")
public class Util {
    public static final PacketCodec<RegistryByteBuf, Unit> UNIT_PACKET_CODEC = PacketCodec.unit(Unit.INSTANCE);

    // Birthday
    private static final LocalDate today = LocalDate.now();
    public static final boolean isAcoBirthday = checkDate(today.getYear(), 9, 12);
    public static final boolean isAprilFools = checkDate(today.getYear(), 4, 1);

    // Aco Utils
    public static Text acoName = Text.literal("AcoYT");
    public static UUID acoUuid = UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd");

    // Friend Stuff
    public static int friendColor = 0x13515d;
    public static int supporterColor = 0x8e010c;
    public static int bothColor = 0x9e58f7;
    public static int blacklistedColor = 0x6e070d;

    public static Text stylizeNames(UUID uuid, Text text) {
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

        return bl ? text.copy().styled(style -> style.withColor(color)) : text;
    }

    public static int convertToHex(String hexString) {
        hexString = hexString.replace("#", ""); // Remove # if present
        return Integer.parseInt(hexString, 16); // Convert to an int
    }

    public static boolean checkDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth).compareTo(today) * today.compareTo(LocalDate.of(year, month, dayOfMonth)) >= 0;
    }

    public static boolean hasAdvantages(Entity entity) {
        return isAco(entity) || isOwnedByAco(entity);
    }

    public static boolean isAco(Entity entity) {
        if (entity == null) return false;
        return entity.getUuid().equals(acoUuid);
    }

    public static boolean isOwnedByAco(Entity entity) {
        if (!(entity instanceof Ownable ownable)) return false;
        return isAco(ownable.getOwner());
    }
}
