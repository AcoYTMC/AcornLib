package net.acoyt.acornlib.impl.mixin.modMenu;

import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.compat.AcornConfig;
import net.acoyt.acornlib.impl.compat.NameColorList;
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
        // Get the mod ID
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

        // ModIds
        if (AcornConfig.displayModIds) {
            drawContext.drawTextWithShadow(font, modId, x + 146, y + 1, ALib.MMM.getOrDefault(modId, 0xFFFFFFFF));
        }

        // ModMenu Color Set
        for (String modIde : ALib.MMM.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.drawTextWithShadow(font, Language.getInstance().reorder(trimmedName), x + iconSize + 3, y + 1, ALib.MMM.get(modId));
            }
        }

        // AcoYT Badge
        boolean bl = mod.getAuthors().contains("AcoYT");
        if (bl) {
            drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, AcornLib.id("acorn.png"), rowWidth - 5, y, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        // ModMenu Icons
        for (String modIde : ALib.MM_ICONS.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, ALib.MM_ICONS.get(modId), rowWidth - (bl ? 17 : 5), y, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        for (String modIde : ALib.MM_MORE_ICONS.keySet()) {
            if (modIde.equals(modId)) {
                drawContext.drawTexture(RenderLayer::getGuiTexturedOverlay, ALib.MM_MORE_ICONS.get(modId), rowWidth - 29, y, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        // Builtin ModMenu Compat Colors
        if (AcornConfig.nameColorCompat) {
            for (String modIde : NameColorList.SPECIAL_MMM.keySet()) {
                if (modIde.equals(modId)) {
                    drawContext.drawTextWithShadow(font, Language.getInstance().reorder(trimmedName), x + iconSize + 3, y + 1, NameColorList.SPECIAL_MMM.get(modId));
                }
            }
        }

        // Author-Specific
        for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
            if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(modId) && !ALib.MMM.containsKey(modId)) {
                drawContext.drawTextWithShadow(font, Language.getInstance().reorder(trimmedName), x + iconSize + 3, y + 1, NameColorList.AUTHOR_SPECIFIC.get(author));
            }
        }
    }
}
