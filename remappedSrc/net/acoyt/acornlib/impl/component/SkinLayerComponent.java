package net.acoyt.acornlib.impl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.entity.EquipmentSlotGroup.ANY;
import static net.minecraft.world.entity.EquipmentSlotGroup.HAND;

public record SkinLayerComponent(ResourceLocation id, EquipmentSlotGroup slot) {
    public static final SkinLayerComponent DEFAULT = new SkinLayerComponent(AcornLib.id("knuckles"), EquipmentSlotGroup.HAND);

    public static final Codec<SkinLayerComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("id").forGetter(SkinLayerComponent::id),
            EquipmentSlotGroup.CODEC.optionalFieldOf("slot", EquipmentSlotGroup.HAND).forGetter(SkinLayerComponent::slot)
    ).apply(instance, SkinLayerComponent::new));

    public SkinLayerComponent withId(ResourceLocation id) {
        return new SkinLayerComponent(id, this.slot);
    }

    public SkinLayerComponent withSlot(EquipmentSlotGroup slot) {
        return new SkinLayerComponent(this.id, slot);
    }

    public static boolean hasStackInCorrectSlot(Player player, ItemStack stack) {
        if (stack.has(AcornComponents.SKIN_LAYER)) {
            SkinLayerComponent component = stack.getOrDefault(AcornComponents.SKIN_LAYER, DEFAULT);
            for (EquipmentSlotGroup slot : EquipmentSlotGroup.values()) {
                EquipmentSlot equipmentSlot = getSlotFromModifierSlot(slot);
                if (component.slot == HAND) {
                    return player.getItemBySlot(EquipmentSlot.MAINHAND) == stack || player.getMainHandItem() == stack || player.getItemBySlot(EquipmentSlot.OFFHAND) == stack || player.getOffhandItem() == stack;
                }

                if (component.slot == slot && equipmentSlot != null) {
                    return player.getItemBySlot(equipmentSlot) == stack;
                }
            }

            return component.slot == ANY;
        }

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

    public static List<ItemStack> getEquippedStacks(Player player) {
        List<ItemStack> list = new ArrayList<>();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.HAND) {
                if (!player.getItemBySlot(slot).isEmpty()) {
                    list.add(player.getItemBySlot(slot));
                }
            }
        }

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isEmpty()) {
                list.add(stack);
            }
        }

        return list;
    }
}
