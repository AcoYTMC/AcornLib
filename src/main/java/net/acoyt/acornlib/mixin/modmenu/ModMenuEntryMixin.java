package net.acoyt.acornlib.mixin.modmenu;

import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.AcornLib;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.util.AcornLibUtils;
import net.minecraft.client.gl.RenderPipelines;
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
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        ModListEntry selectedEntry = this.selected;
        if (selectedEntry != null) {
            Mod mod = selectedEntry.getMod();
            int imageOffset = 36;
            Text name = Text.literal(mod.getTranslatedName());
            StringVisitable trimmedName = name;
            int maxNameWidth = this.width - (this.rightPaneX + imageOffset);
            if (this.textRenderer.getWidth(name) > maxNameWidth) {
                StringVisitable ellipsis = StringVisitable.plain("...");
                trimmedName = StringVisitable.concat(this.textRenderer.trimToWidth(name, maxNameWidth - this.textRenderer.getWidth(ellipsis)), ellipsis);
            }

            for (String modId : ALib.MMM.keySet()) {
                if (modId.equals(mod.getId())) {
                    context.drawText(this.textRenderer, Language.getInstance().reorder(trimmedName), this.rightPaneX + imageOffset, 49, ALib.MMM.get(modId), true);
                }
            }

            if (AcornLib.MOD_ID.equals(mod.getId())) {
                context.drawText(this.textRenderer, Language.getInstance().reorder(trimmedName), this.rightPaneX + imageOffset, 49, AcornLibUtils.modNameColor, true);
                context.drawTexture(RenderPipelines.GUI_TEXTURED, AcornLib.id("acorn.png"), this.rightPaneX + imageOffset + 44, 46, 0.0F, 0.0F, 13, 13, 13, 13);
            }
        }
    }
}
