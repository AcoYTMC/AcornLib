package net.acoyt.acornlib.mixin.helper;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author AcoYT
 */
@Mixin(Item.Properties.class)
public abstract class PropertiesMixin {
    //? if > 1.21.1 {
    /*@WrapMethod(method = "humanoidArmor")
    private Item.Properties acornlib$addArmorMaterialComponent(ArmorMaterial material, ArmorType type, Operation<Item.Properties> original) {
        return original.call(material, type).component(AcornDataComponents.ARMOR_MATERIAL, material);
    }

    @WrapMethod(method = "wolfArmor")
    private Item.Properties acornlib$addWolfArmorMaterialComponent(ArmorMaterial material, Operation<Item.Properties> original) {
        return original.call(material).component(AcornDataComponents.ARMOR_MATERIAL, material);
    }

    @WrapMethod(method = "horseArmor")
    private Item.Properties acornlib$addHorseArmorMaterialComponent(ArmorMaterial material, Operation<Item.Properties> original) {
        return original.call(material).component(AcornDataComponents.ARMOR_MATERIAL, material);
    }

    @WrapMethod(method = "nautilusArmor")
    private Item.Properties acornlib$addNautilusArmorMaterialComponent(ArmorMaterial material, Operation<Item.Properties> original) {
        return original.call(material).component(AcornDataComponents.ARMOR_MATERIAL, material);
    }
    *///? }
}
