package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.GameProfile;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    @Shadow protected abstract boolean canSprint();
    @Shadow protected abstract boolean canVehicleSprint(Entity vehicle);
    @Shadow protected abstract boolean isWalking();

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
    private boolean noSwordSlowdown(ClientPlayerEntity player, @NotNull Operation<Boolean> original) {
        return (original.call(player) && !(player.getActiveItem().getItem() instanceof SprintUsableItem));
    }

    @Inject(method = "canStartSprinting", at = @At("RETURN"), cancellable = true)
    private void gilded$canStartSprintingWithItem(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || (!this.isSprinting()
                && this.isWalking()
                && this.canSprint()
                && this.getActiveItem().getItem() instanceof SprintUsableItem
                && !this.isUsingItem()
                && !this.hasStatusEffect(StatusEffects.BLINDNESS)
                && (!this.hasVehicle() || this.canVehicleSprint(this.getVehicle()))
                && !this.isFallFlying()));
    }
}
