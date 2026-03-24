package net.acoyt.acornlib.mixin.compat.modmenu;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModsScreen.class)
public abstract class ModsScreenMixin extends Screen {
    @Shadow(remap = false) private ModListEntry selected;
    @Shadow(remap = false) private int rightPaneX;

    protected ModsScreenMixin(Text title) {
        super(title);
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;" +
                            "Lnet/minecraft/text/OrderedText;IIIZ)I",
                    ordinal = 6
            )
    )
    private int acornlib$replaceName(DrawContext instance, TextRenderer textRenderer, OrderedText text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        Mod mod = this.selected.getMod();
        return ALib.MM_DATA.containsKey(mod.getId())
                ? instance.drawText(textRenderer, ALib.MM_DATA.get(mod.getId()).name(), x, y, color, shadow)
                : original.call(instance, textRenderer, text, x, y, color, shadow);
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void acornlib$render(DrawContext drawContext, int mouseX, int mouseY, float delta, CallbackInfo ci) {
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

            if (!ALib.MM_DATA.containsKey(mod.getId())) {

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
}
