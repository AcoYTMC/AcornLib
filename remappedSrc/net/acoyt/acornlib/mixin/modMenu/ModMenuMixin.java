package net.acoyt.acornlib.mixin.modMenu;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModListEntry.class)
public abstract class ModMenuMixin {
    @Shadow(remap = false) @Final public Mod mod;
    @Shadow @Final protected Minecraft client;

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void modifyModNameColor(GuiGraphics drawContext, int index, int y, int x, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float delta, CallbackInfo ci) {
        // Get the mod ID
        String modId = this.mod.getId();
        int iconSize = ModMenuConfig.COMPACT_LIST.getValue() ? 19 : 32;

        Component name = Component.literal(this.mod.getTranslatedName());
        FormattedText trimmedName = name;
        int maxNameWidth = rowWidth - iconSize - 3;
        Font font = this.client.font;
        if (font.width(name) > maxNameWidth) {
            FormattedText ellipsis = FormattedText.of("...");
            trimmedName = FormattedText.composite(font.substrByWidth(name, maxNameWidth - font.width(ellipsis)), ellipsis);
        }

        // ModIds
        if (AcornConfig.displayModIds) {
            drawContext.drawString(font, modId, x + 146, y + 1, ALib.MMM.getOrDefault(modId, 0xFFFFFFFF));
        }

        // ModMenu Color Set
        for (String modIde : ALib.MMM.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.drawString(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, ALib.MMM.get(modId));
            }
        }

        // AcoYT Badge
        boolean bl = mod.getAuthors().contains("AcoYT") || mod.getAuthors().contains("AcoYT - Dev");
        if (bl) {
            drawContext.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("acorn.png"), rowWidth - 5, y, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        // ModMenu Icons
        for (String modIde : ALib.MM_ICONS.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.blit(RenderPipelines.GUI_TEXTURED, ALib.MM_ICONS.get(modId), rowWidth - (bl ? 17 : 5), y, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        for (String modIde : ALib.MM_MORE_ICONS.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.blit(RenderPipelines.GUI_TEXTURED, ALib.MM_MORE_ICONS.get(modId), rowWidth - 29, y, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        // Builtin ModMenu Compat Colors
        if (AcornConfig.nameColorCompat) {
            for (String modIde : NameColorList.SPECIAL_MMM.keySet()) {
                if (modIde.equals(modId)) {
                    drawContext.drawString(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.SPECIAL_MMM.get(modId));
                }
            }
        }

        // Author-Specific
        for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
            if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(modId) && !ALib.MMM.containsKey(modId)) {
                drawContext.drawString(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.AUTHOR_SPECIFIC.get(author));
            }
        }
    }
}
