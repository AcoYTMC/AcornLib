package net.acoyt.acornlib.api.helper;

import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.ApiStatus;

//? if > 1.21.1 {
/*import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
*///? } else {
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Equipable;
//? }

import java.util.HashMap;
import java.util.Map;

/**
 * @author AcoYT
 * Must be called inside a static block
 */
@ApiStatus.Experimental
public class ArmorAttributesHelper {
    public static final Map<ResourceLocation, AddAttributes> attributeToMaterial = new HashMap<>();

    public ArmorAttributesHelper() {}

    public static void register(ResourceLocation id, AddAttributes attribute) {
        attributeToMaterial.put(id, attribute);
    }

    //? if > 1.21.1 {
    /*public static ArmorType getType(Equippable component) {
        for (ArmorType type : ArmorType.values()) {
            if (type.getSlot() == component.slot()) {
                return type;
            }
        }

        return ArmorType.BODY;
    }
    *///? } else {
    public static ArmorItem.Type getType(Equipable equipable) {
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            if (type.getSlot() == equipable.getEquipmentSlot()) {
                return type;
            }
        }

        return ArmorItem.Type.BODY;
    }
    //? }

    public static class Event implements DefaultItemComponentEvents.ModifyCallback {
        public void modify(DefaultItemComponentEvents.ModifyContext context) {
            context.modify(
                    //? if > 1.21.1 {
                    /*item -> item.getDefaultInstance().has(DataComponents.EQUIPPABLE)
                     *///? } else {
                    item -> item.getDefaultInstance().getItem() instanceof Equipable
                            //? }
                            && item.getDefaultInstance().has(DataComponents.ATTRIBUTE_MODIFIERS)
                            && item.getDefaultInstance().has(AcornDataComponents.ARMOR_MATERIAL),
                    (builder, item) -> {
                        ItemAttributeModifiers.Builder attributeBuilder = ItemAttributeModifiers.builder();
                        builder.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY).modifiers()
                                .forEach(entry -> attributeBuilder.add(entry.attribute(), entry.modifier(), entry.slot()));

                        //? if > 1.21.1 {
                        /*ArmorType type = getType(builder.getOrDefault(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.BODY).build()));
                         *///? } else {
                        ArmorItem.Type type = getType((Equipable)item);
                        //? }

                        //? if > 1.21.1 {
                        /*ArmorMaterial material = builder.getOrDefault(AcornDataComponents.ARMOR_MATERIAL, ArmorMaterials.IRON);
                         *///? } else {
                        ArmorMaterial material = builder.getOrDefault(AcornDataComponents.ARMOR_MATERIAL, ArmorMaterials.IRON.value());
                        //? }

                        ArmorAttributesHelper.attributeToMaterial.forEach((id, addAttributes) -> {
                            addAttributes.addAttributes().accept(material, type, attributeBuilder);
                        });

                        builder.set(DataComponents.ATTRIBUTE_MODIFIERS, attributeBuilder.build());
                    }
            );
        }
    }

    public interface AddAttributes {
        //? if > 1.21.1 {
        /*TriConsumer<ArmorMaterial, ArmorType, ItemAttributeModifiers.Builder> addAttributes();
         *///? } else {
        TriConsumer<ArmorMaterial, ArmorItem.Type, ItemAttributeModifiers.Builder> addAttributes();
        //? }
    }
}
