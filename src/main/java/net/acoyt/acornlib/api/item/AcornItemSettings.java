package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

/**
 * @author AcoYT
 */
public class AcornItemSettings extends Item.Settings {
    public AcornItemSettings twoHanded() {
        this.component(AcornDataComponents.TWO_HANDED, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings followsCam() {
        this.component(AcornDataComponents.FOLLOWS_CAM, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings showHand() {
        this.component(AcornDataComponents.SHOW_HAND, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings undroppable() {
        this.component(AcornDataComponents.UNDROPPABLE, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings skin(String skin) {
        this.component(AcornDataComponents.SKIN, skin);
        return this;
    }

    public AcornItemSettings hitParticle(Identifier particle) {
        this.component(AcornDataComponents.HIT_PARTICLE, new HitParticleComponent(particle, 0));
        return this;
    }

    public AcornItemSettings hitParticle(Identifier particle, int count) {
        this.component(AcornDataComponents.HIT_PARTICLE, new HitParticleComponent(particle, count));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent) {
        this.component(AcornDataComponents.HIT_SOUND, new HitSoundComponent(soundEvent.getId(), false));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent, boolean randomPitch) {
        this.component(AcornDataComponents.HIT_SOUND, new HitSoundComponent(soundEvent.getId(), randomPitch));
        return this;
    }

    public AcornItemSettings sweepParticle(int base, int shadow) {
        this.component(AcornDataComponents.SWEEP_PARTICLE, new SweepParticleComponent(base, shadow));
        return this;
    }
}
