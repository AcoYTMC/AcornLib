package net.acoyt.acornlib.impl.client.event;

import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.util.exception.SupportersOnlyException;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

public class SupportersOnlyEvent implements ClientTickEvents.EndTick {
    public void onEndTick(MinecraftClient client) {
        if (client.player != null && ALib.getSupporterRequired() && !AcornLib.isSupporter(client.player)) {
            throw new CrashException(new CrashReport("This mod is for supporters only. Consider supporting, it makes everything I do possible :3", new SupportersOnlyException()));
        }
    }
}
