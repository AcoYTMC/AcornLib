package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @WrapOperation(
            method = "tickMovement",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
            )
    )
    private boolean acornlib$noSwordSlowdown(ClientPlayerEntity instance, @NotNull Operation<Boolean> original) {
        return original.call(instance) && !(instance.getActiveItem().getItem() instanceof SprintUsableItem);
    }

    @WrapOperation(
            method = "canStartSprinting",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/ClientPlayerEntity;isUsingItem()Z"
            )
    )
    private boolean acornlib$canStartSprintingWithItem(ClientPlayerEntity instance, Operation<Boolean> original) {
        return original.call(instance) && !(instance.getActiveItem().getItem() instanceof SprintUsableItem);
    }
}
