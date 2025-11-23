package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public record HitSoundComponent(ResourceLocation soundEvent, boolean randomPitch) {
    public static final HitSoundComponent DEFAULT = new HitSoundComponent(ResourceLocation.withDefaultNamespace("intentionally_empty"), false);

    public static final Codec<HitSoundComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("soundEvent").forGetter(HitSoundComponent::soundEvent),
            Codec.BOOL.optionalFieldOf("randomPitch", false).forGetter(HitSoundComponent::randomPitch)
    ).apply(instance, HitSoundComponent::new));
}
