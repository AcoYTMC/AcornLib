package net.acoyt.acornlib.impl.networking;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SyncChangingRulePayload(boolean value) implements CustomPayload {
    public static final Id<SyncChangingRulePayload> ID = new Id<>(AcornLib.id("sync_changing_rule"));

    public static final PacketCodec<RegistryByteBuf, SyncChangingRulePayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL,
            SyncChangingRulePayload::value,
            SyncChangingRulePayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
