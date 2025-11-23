package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.event.SkinOverrideEvent;
import net.acoyt.acornlib.api.event.SkinOverrideEvent.Type;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.entity.EquipmentSlotGroup.ANY;
import static net.minecraft.world.entity.EquipmentSlotGroup.HAND;

public record SkinOverrideComponent(ResourceLocation id, boolean slim, EquipmentSlotGroup slot, Type type) {
    public static final SkinOverrideComponent DEFAULT = new SkinOverrideComponent(AcornLib.id("marlowe"), true, EquipmentSlotGroup.HAND, SkinOverrideEvent.Type.COMPLETELY);

    public static final Codec<SkinOverrideComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(SkinOverrideComponent::id),
            Codec.BOOL.fieldOf("slim").forGetter(SkinOverrideComponent::slim),
            EquipmentSlotGroup.CODEC.fieldOf("slot").orElse(ANY).forGetter(SkinOverrideComponent::slot),
            Codec.STRING.fieldOf("type").orElse("completely")
                    .xmap(Type::fromString, Type::name)
                    .forGetter(SkinOverrideComponent::type)
    ).apply(instance, SkinOverrideComponent::new));

    public SkinOverrideComponent withId(ResourceLocation id) {
        return new SkinOverrideComponent(id, this.slim, this.slot, this.type);
    }

    public SkinOverrideComponent withSlim(boolean slim) {
        return new SkinOverrideComponent(this.id, slim, this.slot, this.type);
    }

    public SkinOverrideComponent withSlot(EquipmentSlotGroup slot) {
        return new SkinOverrideComponent(this.id, this.slim, slot, this.type);
    }

    public static boolean hasStackInCorrectSlot(Player player, ItemStack stack) {
        //if (stack.contains(AcornComponents.SKIN_OVERRIDE)) {
        //    SkinOverrideComponent component = stack.getOrDefault(AcornComponents.SKIN_OVERRIDE, DEFAULT);
        //    for (AttributeModifierSlot slot : AttributeModifierSlot.values()) {
        //        EquipmentSlot equipmentSlot = getSlotFromModifierSlot(slot);
        //        if (component.slot == HAND) {
        //            return player.getEquippedStack(EquipmentSlot.MAINHAND) == stack || player.getMainHandStack() == stack || player.getEquippedStack(EquipmentSlot.OFFHAND) == stack || player.getOffHandStack() == stack;
        //        }

        //        if (component.slot == slot && equipmentSlot != null) {
        //            return player.getEquippedStack(equipmentSlot) == stack;
        //        }
        //    }

        //    return component.slot == ANY;
        //}

        return false;
    }

    @Nullable
    public static EquipmentSlot getSlotFromModifierSlot(EquipmentSlotGroup slot) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (slot == HAND || slot == ANY) {
                return null;
            }

            if (slot.test(equipmentSlot)) {
                return equipmentSlot;
            }
        }

        return null;
    }
}
