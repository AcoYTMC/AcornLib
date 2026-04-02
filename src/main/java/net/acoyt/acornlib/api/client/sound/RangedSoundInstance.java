package net.acoyt.acornlib.api.client.sound;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.AbstractSoundInstance;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

/**
 * @author BouncingElf10
 */
public class RangedSoundInstance extends AbstractSoundInstance implements TickableSoundInstance {
    private final float fadeStart;
    private final float fadeEnd;
    private boolean done = false;

    public RangedSoundInstance(SoundEvent sound, SoundCategory category, Vec3d pos, float fadeStart, float fadeEnd) {
        super(sound, category, Random.create());
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        this.volume = 1.0F;
        this.pitch = 1.0F;
        this.fadeStart = fadeStart;
        this.fadeEnd = fadeEnd;
        this.repeat = false;
        this.repeatDelay = 0;
        this.relative = false;
        this.attenuationType = AttenuationType.NONE;
    }

    public void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.gameRenderer == null || client.gameRenderer.getCamera() == null) {
            this.done = true;
            return;
        }

        double distance = Math.sqrt(client.gameRenderer.getCamera().getPos().squaredDistanceTo(this.x, this.y, this.z));

        if (distance <= fadeStart) {
            this.volume = 1.0f;
        } else if (distance >= fadeEnd) {
            this.volume = 0.0f;
            this.done = true;
        } else {
            this.volume = 1.0f - (float)((distance - fadeStart) / (fadeEnd - fadeStart));
        }
    }

    public boolean isDone() {
        return this.done;
    }
}
