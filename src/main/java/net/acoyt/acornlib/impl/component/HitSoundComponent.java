package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

/**
 * @author AcoYT
 */
public record HitSoundComponent(Identifier soundEvent, boolean randomPitch) {
    public static final HitSoundComponent DEFAULT = new HitSoundComponent(Identifier.ofVanilla("intentionally_empty"), false);

    public static final Codec<HitSoundComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("soundEvent").forGetter(HitSoundComponent::soundEvent),
            Codec.BOOL.fieldOf("randomPitch").forGetter(HitSoundComponent::randomPitch)
    ).apply(instance, HitSoundComponent::new));

    public static final PacketCodec<ByteBuf, HitSoundComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
