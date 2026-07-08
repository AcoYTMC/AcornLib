package net.acoyt.acornlib.api.item;

//~ if > 1.21.1 'getLocation()' -> 'location()' {
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;

/**
 * @author AcoYT
 */
@SuppressWarnings("unused")
public class AcornItemSettings extends Item.Properties {
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

    public AcornItemSettings secondaryModel(ResourceLocation modelId) {
        this.component(AcornDataComponents.SECONDARY_MODEL, modelId);
        return this;
    }

    public AcornItemSettings tertiaryModel(ResourceLocation modelId) {
        this.component(AcornDataComponents.TERTIARY_MODEL, modelId);
        return this;
    }

    public AcornItemSettings hitParticle(ResourceLocation particle) {
        this.component(AcornDataComponents.HIT_PARTICLE, new HitParticleComponent(particle, 0));
        return this;
    }

    public AcornItemSettings hitParticle(ResourceLocation particle, int count) {
        this.component(AcornDataComponents.HIT_PARTICLE, new HitParticleComponent(particle, count));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent) {
        this.component(AcornDataComponents.HIT_SOUND, new HitSoundComponent(soundEvent.getLocation(), false));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent, boolean randomPitch) {
        this.component(AcornDataComponents.HIT_SOUND, new HitSoundComponent(soundEvent.getLocation(), randomPitch));
        return this;
    }

    public AcornItemSettings sweepParticle(int base, int shadow) {
        this.component(AcornDataComponents.SWEEP_PARTICLE, new SweepParticleComponent(base, shadow));
        return this;
    }
}
//~ }