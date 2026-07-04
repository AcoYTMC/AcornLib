package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.util.Util;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = PlayerListHud.class, priority = 500)
public class PlayerListHudMixin {
    @ModifyReturnValue(method = "applyGameModeFormatting", at = @At("RETURN"))
    public Text acornlib$applyFriendFormattingToName(Text original, PlayerListEntry entry) {
        return AcornLib.isMidnightLibLoaded && AcornConfig.allowSupporterNameColors ? Util.stylizeNames(entry.getProfile().getId(), original) : original;
    }
}
