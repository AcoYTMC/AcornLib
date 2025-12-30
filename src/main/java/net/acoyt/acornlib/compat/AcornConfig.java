package net.acoyt.acornlib.compat;

import eu.midnightdust.lib.config.MidnightConfig;

public class AcornConfig extends MidnightConfig {
    private static final String config = "config";
    private static final String debug = "debug";

    @Entry(category = config)
    public static boolean allowSupporterNameColors = true;

    @Entry(category = config)
    public static boolean nameColorCompat = true;

    @Entry(category = debug)
    public static boolean displayModIds = false;
}
