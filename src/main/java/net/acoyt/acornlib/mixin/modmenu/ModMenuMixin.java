package net.acoyt.acornlib.mixin.modmenu;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModListEntry.class)
public abstract class ModMenuMixin {
    @Shadow @Final public Mod mod;
    @Shadow @Final protected MinecraftClient client;

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void modifyModNameColor(DrawContext drawContext, int index, int y, int x, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float delta, CallbackInfo ci) {
        String modId = this.mod.getId();
        int iconSize = ModMenuConfig.COMPACT_LIST.getValue() ? 19 : 32;
        Text name = Text.literal(this.mod.getTranslatedName());
        StringVisitable trimmedName = name;
        int maxNameWidth = rowWidth - iconSize - 3;
        TextRenderer font = this.client.textRenderer;
        if (font.getWidth(name) > maxNameWidth) {
            StringVisitable ellipsis = StringVisitable.plain("...");
            trimmedName = StringVisitable.concat(font.trimToWidth(name, maxNameWidth - font.getWidth(ellipsis)), ellipsis);
        }

        for (String modIde : ALib.MMM.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.drawText(this.client.textRenderer, Language.getInstance().reorder(trimmedName), x + iconSize + 3, y + 1, (Integer) ALib.MMM.get(modId), true);
            }
        }

        if (AcornLib.MOD_ID.equals(modId)) {
            drawContext.drawText(this.client.textRenderer, Language.getInstance().reorder(trimmedName), x + iconSize + 3, y + 1, AcornLibUtils.modNameColor, true);
            drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, AcornLib.id("acorn.png"), x + iconSize + 47, y - 1, 0.0F, 0.0F, 12, 12, 12, 12);
        }
    }
}
