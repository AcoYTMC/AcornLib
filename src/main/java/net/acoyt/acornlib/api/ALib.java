package net.acoyt.acornlib.api;

import net.acoyt.acornlib.api.plush.PlushData;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.block.Block;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.*;

@SuppressWarnings("unused")
public final class ALib {
    private static boolean IS_SUPPORTER_REQUIRED = false;
    public static Map<String, Integer> MMM = new HashMap<>(); // Mod Menu Map
    public static Map<String, Identifier> MM_ICONS = new HashMap<>();
    public static Map<String, Identifier> MM_MORE_ICONS = new HashMap<>();
    public static List<PlushData> plushies = new ArrayList<>();

    public static void setSupporterRequired(boolean supporterRequired) {
        IS_SUPPORTER_REQUIRED = supporterRequired;
    }

    public static boolean getSupporterRequired() {
        return IS_SUPPORTER_REQUIRED;
    }

    public static void registerModMenu(String modId, String hexColor) {
        MMM.put(modId, AcornLibUtils.convertToHex(hexColor));
    }

    public static void registerModMenu(String modId, int decimalColor) {
        MMM.put(modId, decimalColor);
    }

    public static void registerPlush(Block block, SoundEvent soundEvent, int descColor) {
        PlushData assembledData = new PlushData(block, soundEvent, descColor);
        if (!plushies.contains(assembledData)) {
            plushies.add(assembledData);
        }
    }

    /// If mod already has an icon, add a secondary icon
    public static void registerModIcon(String modId, Identifier texturePath) {
        if (MM_ICONS.containsKey(modId)) {
            MM_MORE_ICONS.put(modId, texturePath);
        } else {
            MM_ICONS.put(modId, texturePath);
        }
    }

    /// Registers two mod icons
    public static void registerModIcons(String modId, Identifier mainTexture, Identifier secondaryTexture) {
        MM_ICONS.put(modId, mainTexture);
        MM_MORE_ICONS.put(modId, secondaryTexture);
    }
}
