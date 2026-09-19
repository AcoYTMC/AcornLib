package net.acoyt.acornlib.api.plush;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.Block;

/**
 * @author AcoYT
 */
public record PlushData(Block block, SoundEvent soundEvent, int descColor) {
    public PlushData withSound(SoundEvent soundEvent) {
        return new PlushData(this.block, soundEvent, this.descColor);
    }

    public PlushData withColor(int descColor) {
        return new PlushData(this.block, this.soundEvent, descColor);
    }
}
