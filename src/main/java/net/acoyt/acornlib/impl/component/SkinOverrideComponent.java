package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.api.event.SkinOverrideEvent;
import net.acoyt.acornlib.api.event.SkinOverrideEvent.Type;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.component.type.AttributeModifierSlot.ANY;
import static net.minecraft.component.type.AttributeModifierSlot.HAND;

public record SkinOverrideComponent(Identifier id, boolean slim, AttributeModifierSlot slot, Type type) {
    public static final SkinOverrideComponent DEFAULT = new SkinOverrideComponent(AcornLib.id("marlowe"), true, AttributeModifierSlot.HAND, SkinOverrideEvent.Type.COMPLETELY);

    public static final Codec<SkinOverrideComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(SkinOverrideComponent::id),
            Codec.BOOL.fieldOf("slim").forGetter(SkinOverrideComponent::slim),
            AttributeModifierSlot.CODEC.fieldOf("slot").orElse(ANY).forGetter(SkinOverrideComponent::slot),
            Codec.STRING.fieldOf("type").orElse("completely")
                    .xmap(Type::fromString, Type::name)
                    .forGetter(SkinOverrideComponent::type)
    ).apply(instance, SkinOverrideComponent::new));

    public SkinOverrideComponent withId(Identifier id) {
        return new SkinOverrideComponent(id, this.slim, this.slot, this.type);
    }

    public SkinOverrideComponent withSlim(boolean slim) {
        return new SkinOverrideComponent(this.id, slim, this.slot, this.type);
    }

    public SkinOverrideComponent withSlot(AttributeModifierSlot slot) {
        return new SkinOverrideComponent(this.id, this.slim, slot, this.type);
    }

    public static boolean hasStackInCorrectSlot(PlayerEntity player, ItemStack stack) {
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
    public static EquipmentSlot getSlotFromModifierSlot(AttributeModifierSlot slot) {
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            if (slot == HAND || slot == ANY) {
                return null;
            }

            if (slot.matches(equipmentSlot)) {
                return equipmentSlot;
            }
        }

        return null;
    }
}
