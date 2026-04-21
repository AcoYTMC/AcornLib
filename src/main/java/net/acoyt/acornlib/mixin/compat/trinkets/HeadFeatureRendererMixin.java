package net.acoyt.acornlib.mixin.compat.trinkets;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.acoyt.acornlib.impl.index.tag.AcornItemTags;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

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
    private ItemStack acornlib$changeHeadStack(LivingEntity instance, EquipmentSlot equipmentSlot, Operation<ItemStack> original) {
        ItemStack stack = original.call(instance, equipmentSlot);
        return acornlib$getPlushTrinket(instance).orElse(stack);
    }

    @Unique
    private static Optional<ItemStack> acornlib$getPlushTrinket(LivingEntity living) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(living);
        if (optional.isEmpty()) return Optional.empty();

        TrinketComponent component = optional.get();
        for (Pair<SlotReference, ItemStack> pair : component.getEquipped(stack -> stack.isIn(AcornItemTags.PLUSHIES))) {
            if (pair.getLeft().inventory().getSlotType().getName().equals("hat") && pair.getRight().isIn(AcornItemTags.PLUSHIES)) {
                return Optional.of(pair.getRight());
            }
        }

        return Optional.empty();
    }
}
