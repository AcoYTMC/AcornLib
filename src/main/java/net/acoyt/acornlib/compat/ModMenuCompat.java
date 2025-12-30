package net.acoyt.acornlib.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import net.acoyt.acornlib.impl.AcornLib;

public class ModMenuCompat implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> MidnightConfig.getScreen(parent, AcornLib.MOD_ID);
    }
}
