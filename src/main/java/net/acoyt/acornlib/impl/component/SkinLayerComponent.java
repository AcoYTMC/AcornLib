package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.component.type.AttributeModifierSlot.ANY;
import static net.minecraft.component.type.AttributeModifierSlot.HAND;

public record SkinLayerComponent(Identifier id, AttributeModifierSlot slot) {
    public static final SkinLayerComponent DEFAULT = new SkinLayerComponent(AcornLib.id("knuckles"), AttributeModifierSlot.HAND);

    public static final Codec<SkinLayerComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(SkinLayerComponent::id),
            AttributeModifierSlot.CODEC.optionalFieldOf("slot", AttributeModifierSlot.HAND).forGetter(SkinLayerComponent::slot)
    ).apply(instance, SkinLayerComponent::new));

    public SkinLayerComponent withId(Identifier id) {
        return new SkinLayerComponent(id, this.slot);
    }

    public SkinLayerComponent withSlot(AttributeModifierSlot slot) {
        return new SkinLayerComponent(this.id, slot);
    }

    public static boolean hasStackInCorrectSlot(PlayerEntity player, ItemStack stack) {
        if (stack.contains(AcornComponents.SKIN_LAYER)) {
            SkinLayerComponent component = stack.getOrDefault(AcornComponents.SKIN_LAYER, DEFAULT);
            for (AttributeModifierSlot slot : AttributeModifierSlot.values()) {
                EquipmentSlot equipmentSlot = getSlotFromModifierSlot(slot);
                if (component.slot == HAND) {
                    return player.getEquippedStack(EquipmentSlot.MAINHAND) == stack || player.getMainHandStack() == stack || player.getEquippedStack(EquipmentSlot.OFFHAND) == stack || player.getOffHandStack() == stack;
                }

                if (component.slot == slot && equipmentSlot != null) {
                    return player.getEquippedStack(equipmentSlot) == stack;
                }
            }

            return component.slot == ANY;
        }

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

    public static List<ItemStack> getEquippedStacks(PlayerEntity player) {
        List<ItemStack> list = new ArrayList<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.HAND) {
                if (!player.getEquippedStack(slot).isEmpty()) {
                    list.add(player.getEquippedStack(slot));
                }
            }
        }

        for (Hand hand : Hand.values()) {
            ItemStack stack = player.getStackInHand(hand);
            if (!stack.isEmpty()) {
                list.add(stack);
            }
        }

        return list;
    }
}
