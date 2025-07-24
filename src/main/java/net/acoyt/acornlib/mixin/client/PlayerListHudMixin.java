package net.acoyt.acornlib.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerListHud.class)
public class PlayerListHudMixin {
    @ModifyReturnValue(
            method = "applyGameModeFormatting",
            at = @At("RETURN")
    )
    public Text acornLib$applyFriendFormattingToName(Text original, PlayerListEntry entry) {
        return AcornConfig.allowSupporterNameColors ? AcornLibUtils.stylizeNames(entry.getProfile().getId(), original) : original;
    }
}
