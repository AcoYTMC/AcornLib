package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.util.AcornLibUtils;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerTabOverlay.class)
public class PlayerListHudMixin {
    @ModifyReturnValue(
            method = "applyGameModeFormatting",
            at = @At("RETURN")
    )
    public Component acornLib$applyFriendFormattingToName(Component original, PlayerInfo entry) {
        return AcornConfig.allowSupporterNameColors ? AcornLibUtils.stylizeNames(entry.getProfile().getId(), original) : original;
    }
}
