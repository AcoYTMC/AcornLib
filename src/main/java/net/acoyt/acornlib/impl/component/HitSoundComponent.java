package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

/**
 * @author AcoYT
 */
public record HitSoundComponent(Identifier soundEvent, boolean randomPitch) {
    public static final HitSoundComponent DEFAULT = new HitSoundComponent(Identifier.withDefaultNamespace("intentionally_empty"), false);

    public static final Codec<HitSoundComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("soundEvent").forGetter(HitSoundComponent::soundEvent),
            Codec.BOOL.fieldOf("randomPitch").forGetter(HitSoundComponent::randomPitch)
    ).apply(instance, HitSoundComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, HitSoundComponent> PACKET_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, HitSoundComponent::soundEvent,
            ByteBufCodecs.BOOL, HitSoundComponent::randomPitch,
            HitSoundComponent::new
    );
}
