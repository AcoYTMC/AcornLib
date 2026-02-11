package net.acoyt.acornlib.impl.networking;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record ForcePerspectivePayload(String perspective) implements CustomPayload {
    public static final CustomPayload.Id<ForcePerspectivePayload> ID = new Id<>(AcornLib.id("force_perspective"));

    public static final PacketCodec<RegistryByteBuf, ForcePerspectivePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING,
            ForcePerspectivePayload::perspective,
            ForcePerspectivePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
