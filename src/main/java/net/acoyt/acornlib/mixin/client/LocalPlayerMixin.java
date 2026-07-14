package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mojang.authlib.GameProfile;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    public LocalPlayerMixin(ClientLevel level, GameProfile profile) {
        super(level, profile);
    }

    @ModifyReturnValue(method = "isSlowDueToUsingItem", at = @At("RETURN"))
    private boolean acornlib$canStartSprintingWithItem(boolean original) {
        return original && !(this.useItem.getItem() instanceof SprintUsableItem);
    }

    @ModifyExpressionValue(
            method = "modifyInput",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
            )
    )
    private boolean acornlib$noSwordSlowdown(boolean original) {
        return original && !(this.useItem.getItem() instanceof SprintUsableItem);
    }
}
