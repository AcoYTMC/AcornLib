package net.acoyt.acornlib.impl.util;

import io.netty.buffer.ByteBuf;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TraceableEntity;

import java.time.LocalDate;
import java.util.UUID;

import static net.acoyt.acornlib.impl.AcornLib.supporters;

//? if > 1.21.1 {
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
//? }

/**
 * @author AcoYT
 */
public class AcornUtil {
    //? if > 1.21.1 {
    public static final Codec<ArmorMaterial> ARMOR_MATERIAL_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("durability").forGetter(ArmorMaterial::durability),
            Codec.unboundedMap(ArmorType.CODEC, Codec.INT).fieldOf("defense").forGetter(ArmorMaterial::defense),
            Codec.INT.fieldOf("enchantmentValue").forGetter(ArmorMaterial::enchantmentValue),
            SoundEvent.CODEC.fieldOf("equipSound").forGetter(ArmorMaterial::equipSound),
            Codec.FLOAT.fieldOf("toughness").forGetter(ArmorMaterial::toughness),
            Codec.FLOAT.fieldOf("knockbackResistance").forGetter(ArmorMaterial::knockbackResistance),
            TagKey.hashedCodec(Registries.ITEM).fieldOf("repairIngredient").forGetter(ArmorMaterial::repairIngredient),
            ResourceKey.codec(EquipmentAssets.ROOT_ID).fieldOf("assetId").forGetter(ArmorMaterial::assetId)
    ).apply(instance, ArmorMaterial::new));

    public static final StreamCodec<ByteBuf, ArmorMaterial> ARMOR_MATERIAL_PACKET_CODEC = ByteBufCodecs.fromCodec(ARMOR_MATERIAL_CODEC);
    //? }

    public static final StreamCodec<ByteBuf, Unit> UNIT_STREAM_CODEC = ByteBufCodecs.fromCodec(Unit.CODEC);

    // Birthday
    private static final LocalDate today = LocalDate.now();
    public static final boolean isAcoBirthday = checkDate(today.getYear(), 9, 12);
    public static final boolean isAprilFools = checkDate(today.getYear(), 4, 1);

    // Aco Utils
    public static Component acoName = Component.literal("AcoYT");
    public static UUID acoUuid = UUID.fromString("017f5cdc-086b-4d98-a0c2-7dc43d5117bd");

    // Friend Stuff
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

    public static boolean checkDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth).compareTo(today) * today.compareTo(LocalDate.of(year, month, dayOfMonth)) >= 0;
    }

    public static boolean hasAdvantages(Entity entity) {
        return isAco(entity) || isOwnedByAco(entity);
    }

    public static boolean isAco(Entity entity) {
        if (entity == null) return false;
        return entity.getUUID().equals(acoUuid);
    }

    public static boolean isOwnedByAco(Entity entity) {
        if (!(entity instanceof TraceableEntity ownable)) return false;
        return isAco(ownable.getOwner());
    }

    public static boolean hasPermissions(CommandSourceStack source) {
        //? if > 1.21.10 {
        return source.permissions().hasPermission(new Permission.HasCommandLevel(PermissionLevel.GAMEMASTERS));
         //? } else {
        /*return source.hasPermission(2);
        *///? }
    }
}
