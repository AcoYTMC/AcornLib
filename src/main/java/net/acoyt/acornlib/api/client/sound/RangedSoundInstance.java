package net.acoyt.acornlib.api.client.sound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;

/**
 * @author BouncingElf10
 */
public class RangedSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {
    private final float fadeStart;
    private final float fadeEnd;
    private boolean done = false;

    public RangedSoundInstance(SoundEvent sound, SoundSource source, Vec3 pos, float fadeStart, float fadeEnd) {
        super(sound, source, RandomSource.create());
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        this.volume = 1.0F;
        this.pitch = 1.0F;
        this.fadeStart = fadeStart;
        this.fadeEnd = fadeEnd;
        this.looping = false;
        this.delay = 0;
        this.relative = false;
        this.attenuation = Attenuation.NONE;
    }

    public void tick() {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) {
            this.done = true;
            return;
        }

        //? if > 1.21.10 {
        double distance = Math.sqrt(client.gameRenderer.getMainCamera().position().distanceToSqr(this.x, this.y, this.z));
         //? } else {
        /*double distance = Math.sqrt(client.gameRenderer.getMainCamera().getPosition().distanceToSqr(this.x, this.y, this.z));
        *///? }

        if (distance <= fadeStart) {
            this.volume = 1.0f;
        } else if (distance >= fadeEnd) {
            this.volume = 0.0f;
            this.done = true;
        } else {
            this.volume = 1.0f - (float)((distance - fadeStart) / (fadeEnd - fadeStart));
        }
    }

    public boolean isStopped() {
        return this.done;
    }
}
