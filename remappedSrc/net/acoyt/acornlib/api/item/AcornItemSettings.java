package net.acoyt.acornlib.api.item;

import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SkinLayerComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;

@SuppressWarnings("unused")
public class AcornItemSettings extends Item.Properties {
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

    public AcornItemSettings secondaryModel(ResourceLocation modelId) {
        this.component(AcornComponents.SECONDARY_MODEL, modelId);
        return this;
    }

    public AcornItemSettings tertiaryModel(ResourceLocation modelId) {
        this.component(AcornComponents.TERTIARY_MODEL, modelId);
        return this;
    }

    public AcornItemSettings skinLayer(ResourceLocation textureId) {
        this.component(AcornComponents.SKIN_LAYER, new SkinLayerComponent(textureId, EquipmentSlotGroup.HAND));
        return this;
    }

    public AcornItemSettings skinLayer(ResourceLocation textureId, EquipmentSlotGroup slot) {
        this.component(AcornComponents.SKIN_LAYER, new SkinLayerComponent(textureId, slot));
        return this;
    }

    public AcornItemSettings hitParticle(ResourceLocation particle) {
        this.component(AcornComponents.HIT_PARTICLE, new HitParticleComponent(particle, 0));
        return this;
    }

    public AcornItemSettings hitParticle(ResourceLocation particle, int count) {
        this.component(AcornComponents.HIT_PARTICLE, new HitParticleComponent(particle, count));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent) {
        this.component(AcornComponents.HIT_SOUND, new HitSoundComponent(soundEvent.location(), false));
        return this;
    }

    public AcornItemSettings hitSound(SoundEvent soundEvent, boolean randomPitch) {
        this.component(AcornComponents.HIT_SOUND, new HitSoundComponent(soundEvent.location(), randomPitch));
        return this;
    }

    public AcornItemSettings sweepParticle(int base, int shadow) {
        this.component(AcornComponents.SWEEP_PARTICLE, new SweepParticleComponent(base, shadow));
        return this;
    }
}
