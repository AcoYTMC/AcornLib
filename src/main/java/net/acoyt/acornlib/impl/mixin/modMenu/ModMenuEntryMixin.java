package net.acoyt.acornlib.impl.mixin.modMenu;

import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.impl.compat.AcornConfig;
import net.acoyt.acornlib.impl.compat.NameColorList;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class ModMenuEntryMixin extends Screen {
    @Shadow private ModListEntry selected;
    @Shadow private int rightPaneX;

    protected ModMenuEntryMixin(Text title) {
        super(title);
    }

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    public void render(DrawContext drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ModListEntry selectedEntry = this.selected;
        if (selectedEntry != null) {
            Mod mod = selectedEntry.getMod();
            int x = this.rightPaneX;
            int imageOffset = 36;
            Text name = Text.literal(mod.getTranslatedName());
            StringVisitable trimmedName = name;
            int maxNameWidth = this.width - (x + imageOffset);
            if (this.textRenderer.getWidth(name) > maxNameWidth) {
                StringVisitable ellipsis = StringVisitable.plain("...");
                trimmedName = StringVisitable.concat(this.textRenderer.trimToWidth(name, maxNameWidth - this.textRenderer.getWidth(ellipsis)), ellipsis);
            }

            // ModMenu Color Set
            for (String modId : ALib.MMM.keySet()) {
                if (modId.equals(mod.getId())) {
                    drawContext.drawTextWithShadow(this.textRenderer, Language.getInstance().reorder(trimmedName), x + imageOffset, 49, ALib.MMM.get(modId));
                }
            }

            // Builtin ModMenu Compat Colors
            if (AcornConfig.nameColorCompat) {
                for (String modId : NameColorList.SPECIAL_MMM.keySet()) {
                    if (modId.equals(mod.getId())) {
                        drawContext.drawTextWithShadow(this.textRenderer, Language.getInstance().reorder(trimmedName), x + imageOffset, 49, NameColorList.SPECIAL_MMM.get(modId));
                    }
                }
            }

            // Author-Specific
            for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
                if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(mod.getId()) && !ALib.MMM.containsKey(mod.getId())) {
                    drawContext.drawTextWithShadow(this.textRenderer, Language.getInstance().reorder(trimmedName), x + imageOffset, 49, NameColorList.AUTHOR_SPECIFIC.get(author));
                }
            }

            // Starts-with / Prefixes
            if (AcornConfig.nameColorCompat) {
                for (String prefix : NameColorList.STARTS_WITH.keySet()) {
                    if (mod.getId().startsWith(prefix)) {
                        drawContext.drawTextWithShadow(this.textRenderer, Language.getInstance().reorder(trimmedName), x + imageOffset, 49, NameColorList.STARTS_WITH.get(prefix));
                    }
                }
            }
        }
    }
}
