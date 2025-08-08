package net.acoyt.acornlib.api;

import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public final class ALib {
    private static boolean IS_SUPPORTER_REQUIRED = false;
    public static Map<String, Integer> MMM = new HashMap<>(); // Mod Menu Map
    public static Map<String, Identifier> MM_ICONS = new HashMap<>();
    public static Map<String, Identifier> MM_MORE_ICONS = new HashMap<>();

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
