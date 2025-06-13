package net.acoyt.acornlib.api;

import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.util.AcornLibUtils;

import java.util.HashMap;
import java.util.Map;

public final class ALib {
    private static boolean IS_SUPPORTER_REQUIRED = false;
    public static Map<String, Integer> MMM = new HashMap<>(); // Mod Menu Map

    public static void init(String modId) {
        AcornLib.currentModId = modId;
    }

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
}
