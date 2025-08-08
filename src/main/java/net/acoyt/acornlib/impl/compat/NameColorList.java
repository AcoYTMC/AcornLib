package net.acoyt.acornlib.impl.compat;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SpellCheckingInspection")
public class NameColorList {
    public static Map<String, Integer> SPECIAL_MMM = new HashMap<>();
    public static Map<String, Integer> AUTHOR_SPECIFIC = new HashMap<>();

    private static final int DEBUG_COLOR = 0xFF870000;

    public static final int MIXIN_COLOR = 0xFFf6de15;
    public static final int FABRIC_COLOR = 0xFFdacc98;

    static {
        // Special Fabric
        registerModMenu("fabric-language-kotlin", 0xFF6d4374);

        // jellysquid3
        registerModMenu("sodium", 0xFF94fca9);
        registerModMenu("lithium", 0xFFc17bf8);

        // shedaniel
        registerModMenu("cloth-config", 0xFF13a34f);
        registerModMenu("roughlyenoughitems", 0xFF243631);
        registerModMenu("architectury-api", 0xFFd16745);

        // coderbot
        registerModMenu("iris", 0xFFb939d7);

        // tr7zw
        registerModMenu("entityculling", 0xFF636e72);
        registerModMenu("notenoughanimations", 0xFF5ebf6c);
        registerModMenu("skinlayers3d", 0xFFa19662);
        registerModMenu("waveycapes", 0xFF901315);
        registerModMenu("firstperson", 0xFF31839c);
        registerModMenu("tr7zw-api-parent", 0xFF636e72);

        // malte0811
        registerModMenu("ferritecore", 0xFF582813);

        // Prospector
        registerModMenu("modmenu", 0xFF0153ec);

        // Motschen
        registerModMenu("puzzle", 0xFFdd1020);
        registerModMenu("midnightlib", 0xFF90a9b2);
        registerModMenu("blur", 0xFF657187);
        registerModMenu("midnightcontrols", 0xFFef2de4);

        // RaphiMC
        registerModMenu("immediatelyfast", 0xFF0aa6a6);
        registerModMenu("viafabricplus", 0xFF03253a);

        // isxander
        registerModMenu("yacl", 0xFFa76c33);
        registerModMenu("zoomify", 0xFF02335a);
        registerModMenu("debugify", 0xFF7d5f57);
        registerModMenu("controlify", 0xFFded99f);
        registerModMenu("isxander-main-menu-credits", 0xFF62c659);

        // xaero96
        registerModMenu("xaerominimap", 0xFFc8cda8);
        registerModMenu("xaeroworldmap", 0xFF387705);

        // FlashyReese
        registerModMenu("sodium-extra", 0xFFe5cd4e);
        registerModMenu("reeses-sodium-options", 0xFF660aa3);

        // Traben
        registerModMenu("entity_texture_features", 0xFF272e80);
        registerModMenu("entity_model_features", 0xFF272e80);

        // embeddedt
        registerModMenu("modernfix", 0xFFbfcbe1);
        registerModMenu("lootr", 0xFFdfbe59);
        registerModMenu("zume", 0xFFb4a8d5);

        // squeek502
        registerModMenu("appleskin", 0xFFb02c3b);

        // PepperCode1
        registerModMenu("continuity", 0xFF339e31);
        registerModMenu("modelfix", 0xFF7a5857);

        // juliand665
        registerModMenu("dynamic_fps", 0xFFd785ec);

        // comp500
        registerModMenu("mixintrace", 0xFFdfbe59);

        // Serilum
        registerModMenu("collective", 0xFF15131a);
        registerModMenu("villagernames", 0xFF9ed08c);
        registerModMenu("starterkit", 0xFF73984c);
        registerModMenu("doubledoors", 0xFF9c5b1f);
        registerModMenu("dismountentity", 0xFFa47282);
        registerModMenu("fullbrightnesstoggle", 0xFFc36a08);
        registerModMenu("infinitetrading", 0xFF9ed08c);

        // henkelmax
        registerModMenu("voicechat", 0xFF424242);
        registerModMenu("sound_physics_remastered", 0xFF424242);

        // Ladysnake
        registerModMenu("blast", 0xFF211f1f);

        // MoriyaShiine
        registerModMenu("respawnablepets", 0xFF285d2c);
        registerModMenu("anthropophagy", 0xFFd6535d);
        registerModMenu("inferno", 0xFFff8b29);
        registerModMenu("superbsteeds", 0xFF7a5857);
        registerModMenu("strawberrylib", 0xFFa6354d);
        registerModMenu("enchancement", 0xFF41933a);
        registerModMenu("hearty-meals", 0xFFf33b39);

        // Vectorwing
        registerModMenu("farmersdelight", 0xFF9f7e4d);

        // Sjouwer
        registerModMenu("gammautils", 0xFF30e699);

        // K-TEAM
        registerModMenu("stickynotes", 0xFFd2fa43);

        // Patbox
        registerModMenu("placeholder-api", 0xFF645e59);
        registerModMenu("universal-graves", 0xFF625649);
        registerModMenu("entity-view-distance", 0xFFffa108);
        registerModMenu("polymer", 0xFF4b90af);
        registerModMenu("image2map", 0xFF00317e);
        registerModMenu("styled-nicknames", 0xFFe75d4d);
        registerModMenu("armor-stand-editory", 0xFF146dfb);

        // Other
        registerModMenu("accurateblockplacement", 0xFF1f4d1a);
        registerModMenu("carpet", 0xFF9e9e9e);
        registerModMenu("clientsort", 0xFF1a244d);
        registerModMenu("essential", 0xFF2c649c);
        registerModMenu("lambdynlights", 0xFFbd7024);
        registerModMenu("tooltipfix", 0xFF636e72);
        registerModMenu("mousetweaks", 0xFF464f49);
        registerModMenu("rebind_narrator", 0xFF315a94);

        // Minecraft
        registerModMenu("minecraft", 0xFF2c5c1b);

        // Debug
        registerModMenu("error_notifier", DEBUG_COLOR);

        // Author-Specific
        registerAuthorSpecific("FabricMC", FABRIC_COLOR);
        registerAuthorSpecific("LlamaLad7", MIXIN_COLOR);
    }

    public static void registerModMenu(String modId, int color) {
        SPECIAL_MMM.put(modId, color);
    }

    public static void registerAuthorSpecific(String authorName, int color) {
        AUTHOR_SPECIFIC.put(authorName, color);
    }
}
