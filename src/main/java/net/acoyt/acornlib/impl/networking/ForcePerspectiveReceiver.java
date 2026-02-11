package net.acoyt.acornlib.impl.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;

import static net.acoyt.acornlib.impl.cca.entity.AcornData.KEY;

public class ForcePerspectiveReceiver {
    public static void registerClientPacket() {
        ClientPlayNetworking.registerGlobalReceiver(ForcePerspectivePayload.ID, (payload, context) -> context.client().execute(() -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null || client.player == null) return;
            Perspective perspective = null;
            for (Perspective per : Perspective.values()) {
                if (per.name().equalsIgnoreCase(payload.perspective())) {
                    perspective = per;
                }
            }

            if (perspective != null) {
                client.options.setPerspective(perspective);
                KEY.get(client.player).setStoredPerspective(payload.perspective());
            }
        }));
    }
}
