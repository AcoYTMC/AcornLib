package net.acoyt.acornlib.impl.networking;

import net.acoyt.acornlib.impl.index.AcornGameRules;
import net.acoyt.acornlib.mixin.access.WorldAccessor;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;

public class SyncChangingRuleReceiver {
    public static void registerClientPacket() {
        ClientPlayNetworking.registerGlobalReceiver(SyncChangingRulePayload.ID, (payload, context) -> context.client().execute(() -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.world == null) return;

            ((WorldAccessor)client.world).aLib$getProperties().getGameRules().get(AcornGameRules.ALLOW_PERSPECTIVE_CHANGING).set(payload.value(), null);
        }));
    }
}
