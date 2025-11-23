package net.acoyt.acornlib.api.plush;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;

public record PlushData(Block block, SoundEvent soundEvent, int descColor) {
    public static final Codec<PlushData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Block.CODEC.fieldOf("block").forGetter(PlushData::block),
            SoundEvent.DIRECT_CODEC.fieldOf("soundEvent").forGetter(PlushData::soundEvent),
            Codec.INT.fieldOf("descColor").forGetter(PlushData::descColor)
    ).apply(instance, PlushData::new));

    public PlushData withSound(SoundEvent soundEvent) {
        return new PlushData(this.block, soundEvent, this.descColor);
    }

    public PlushData withColor(int descColor) {
        return new PlushData(this.block, this.soundEvent, this.descColor);
    }
}
