package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.authlib.GameProfile;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if > 1.21.1
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

/**
 * @author AcoYT
 */
@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    public LocalPlayerMixin(ClientLevel level, GameProfile profile) {
        super(level, profile);
    }

    //? if > 1.21.1 {
    @ModifyReturnValue(method = "isSlowDueToUsingItem", at = @At("RETURN"))
    //? } else {
    /*@ModifyExpressionValue(
            method = "canStartSprinting",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
            )
    )
    *///? }
    private boolean acornlib$canStartSprintingWithItem(boolean original) {
        return original && !(this.useItem.getItem() instanceof SprintUsableItem);
    }

    //? if > 1.21.1 {
    @ModifyExpressionValue(
            method = "modifyInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
            )
    )
    //? } else {
    /*@ModifyExpressionValue(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
            )
    )
    *///? }
    private boolean acornlib$noSwordSlowdown(boolean original) {
        return original && !(this.useItem.getItem() instanceof SprintUsableItem);
    }
}
