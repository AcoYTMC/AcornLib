package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.util.AcornUtil;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(PlayerTabOverlay.class)
public class PlayerTabOverlayMixin {
    @ModifyReturnValue(method = "decorateName", at = @At("RETURN"))
    public Component acornlib$applyFriendFormattingToName(Component original, PlayerInfo info) {
        return AcornLib.isMidnightLibLoaded && AcornConfig.allowSupporterNameColors ? AcornUtil.stylizeNames(info.getProfile().id(), original) : original;
    }
}
