package net.acoyt.acornlib.mixin.compat.trinkets;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.acoyt.acornlib.compat.TrinketsCompat;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin {
    @WrapOperation(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getEquippedStack(Lnet/minecraft/entity/EquipmentSlot;)Lnet/minecraft/item/ItemStack;"
            )
    )
    private ItemStack changeHeadStack(LivingEntity instance, EquipmentSlot equipmentSlot, Operation<ItemStack> original) {
        ItemStack stack = original.call(instance, equipmentSlot);
        return TrinketsCompat.hasPlushTrinket(instance) ? TrinketsCompat.getPlushTrinket(instance).orElse(stack) : stack;
    }
}
