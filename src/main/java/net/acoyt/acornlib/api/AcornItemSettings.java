package net.acoyt.acornlib.api;

import net.acoyt.acornlib.component.HitParticleComponent;
import net.acoyt.acornlib.component.HitSoundComponent;
import net.acoyt.acornlib.component.SweepParticleComponent;
import net.acoyt.acornlib.init.AcornComponents;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public class AcornItemSettings extends Item.Settings {
    public AcornItemSettings twoHanded() {
        this.component(AcornComponents.TWO_HANDED, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings followsCam() {
        this.component(AcornComponents.FOLLOWS_CAM, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings showHand() {
        this.component(AcornComponents.SHOW_HAND, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings undroppable() {
        this.component(AcornComponents.UNDROPPABLE, Unit.INSTANCE);
        return this;
    }

    public AcornItemSettings skin(String skin) {
        this.component(AcornComponents.SKIN, skin);
        return this;
    }

    public AcornItemSettings hitParticle(Identifier particle) {
        this.component(AcornComponents.HIT_PARTICLE, new HitParticleComponent(particle, 0));
        return this;
    }

    public AcornItemSettings hitParticle(Identifier particle, int count) {
        this.component(AcornComponents.HIT_PARTICLE, new HitParticleComponent(particle, count));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent) {
        this.component(AcornComponents.HIT_SOUND, new HitSoundComponent(soundEvent.id(), false));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent, boolean randomPitch) {
        this.component(AcornComponents.HIT_SOUND, new HitSoundComponent(soundEvent.id(), randomPitch));
        return this;
    }

    public AcornItemSettings sweepParticle(int base, int shadow) {
        this.component(AcornComponents.SWEEP_PARTICLE, new SweepParticleComponent(base, shadow));
        return this;
    }
}
