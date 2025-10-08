package net.acoyt.acornlib.impl.client.event;

import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.util.exception.BlacklistedException;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;

public class BlacklistEvent implements ClientTickEvents.EndTick{
    public void onEndTick(MinecraftClient client) {
        if (client.player != null && AcornLib.isBlacklisted(client.player)) {
            throw new CrashException(new CrashReport("A critical error has occurred and the application will now exit.", new BlacklistedException()));
        }
    }
}
