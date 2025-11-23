package net.acoyt.acornlib.mixin.modMenu;

import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class ModMenuEntryMixin extends Screen {
    @Shadow(remap = false) private ModListEntry selected;
    @Shadow(remap = false) private int rightPaneX;

    protected ModMenuEntryMixin(Component title) {
        super(title);
    }

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    public void render(GuiGraphics drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ModListEntry selectedEntry = this.selected;
        if (selectedEntry != null) {
            Mod mod = selectedEntry.getMod();
            int x = this.rightPaneX;
            int imageOffset = 36;
            Component name = Component.literal(mod.getTranslatedName());
            FormattedText trimmedName = name;
            int maxNameWidth = this.width - (x + imageOffset);
            if (this.font.width(name) > maxNameWidth) {
                FormattedText ellipsis = FormattedText.of("...");
                trimmedName = FormattedText.composite(this.font.substrByWidth(name, maxNameWidth - this.font.width(ellipsis)), ellipsis);
            }

            // ModMenu Color Set
            for (String modId : ALib.MMM.keySet()) {
                if (modId.equals(mod.getId())) {
                    drawContext.drawString(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, ALib.MMM.get(modId));
                }
            }

            // Builtin ModMenu Compat Colors
            if (AcornConfig.nameColorCompat) {
                for (String modId : NameColorList.SPECIAL_MMM.keySet()) {
                    if (modId.equals(mod.getId())) {
                        drawContext.drawString(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, NameColorList.SPECIAL_MMM.get(modId));
                    }
                }
            }

            // Author-Specific
            for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
                if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(mod.getId()) && !ALib.MMM.containsKey(mod.getId())) {
                    drawContext.drawString(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, NameColorList.AUTHOR_SPECIFIC.get(author));
                }
            }
        }
    }
}
