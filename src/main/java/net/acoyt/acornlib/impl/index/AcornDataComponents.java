package net.acoyt.acornlib.impl.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.component.HitParticleComponent;
import net.acoyt.acornlib.impl.component.HitSoundComponent;
import net.acoyt.acornlib.impl.component.SweepParticleComponent;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

//? if > 1.21.1 {
/*import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
*///? } else {
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
//? }

/**
 * @author AcoYT
 */
public interface AcornDataComponents {
    DataComponentTypeRegistrant COMPONENTS = new DataComponentTypeRegistrant(AcornLib.MOD_ID);

    DataComponentType<Unit> TWO_HANDED = COMPONENTS.register("two_handed", Unit.CODEC, AcornUtil.UNIT_STREAM_CODEC);
    DataComponentType<Unit> FOLLOWS_CAM = COMPONENTS.register("follows_cam", Unit.CODEC, AcornUtil.UNIT_STREAM_CODEC);
    DataComponentType<Unit> SHOW_HAND = COMPONENTS.register("show_hand", Unit.CODEC, AcornUtil.UNIT_STREAM_CODEC);
    DataComponentType<Unit> UNDROPPABLE = COMPONENTS.register("undroppable", Unit.CODEC, AcornUtil.UNIT_STREAM_CODEC);
    DataComponentType<Unit> IMMOVABLE = COMPONENTS.register("immovable", Unit.CODEC, AcornUtil.UNIT_STREAM_CODEC);

    DataComponentType<String> SKIN = COMPONENTS.register("skin", Codec.STRING, ByteBufCodecs.STRING_UTF8);
    DataComponentType<ResourceLocation> SECONDARY_MODEL = COMPONENTS.register("secondary_model", ResourceLocation.CODEC, ResourceLocation.STREAM_CODEC);
    DataComponentType<ResourceLocation> TERTIARY_MODEL = COMPONENTS.register("tertiary_model", ResourceLocation.CODEC, ResourceLocation.STREAM_CODEC);

    // Other
    DataComponentType<HitParticleComponent> HIT_PARTICLE = COMPONENTS.register("hit_particle", HitParticleComponent.CODEC, HitParticleComponent.PACKET_CODEC);
    DataComponentType<HitSoundComponent> HIT_SOUND = COMPONENTS.register("hit_sound", HitSoundComponent.CODEC, HitSoundComponent.PACKET_CODEC);
    DataComponentType<SweepParticleComponent> SWEEP_PARTICLE = COMPONENTS.register("sweep_particle", SweepParticleComponent.CODEC, SweepParticleComponent.PACKET_CODEC);

    //? if > 1.21.1 {
    /*DataComponentType<ArmorMaterial> ARMOR_MATERIAL = COMPONENTS.register("armor_material", AcornUtil.ARMOR_MATERIAL_CODEC, AcornUtil.ARMOR_MATERIAL_PACKET_CODEC);
    *///? }

    static void init() {}
}
