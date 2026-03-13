package net.acoyt.acornlib.mixin.compat.modmenu;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(ModListEntry.class)
public abstract class ModListEntryMixin {
    @Shadow(remap = false) @Final public Mod mod;
    @Shadow @Final protected MinecraftClient client;

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;" +
                            "Lnet/minecraft/text/OrderedText;IIIZ)I"
            )
    )
    private int replaceName(DrawContext instance, TextRenderer textRenderer, OrderedText text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        return ALib.MM_DATA.containsKey(mod.getId())
                ? instance.drawText(textRenderer, ALib.MM_DATA.get(mod.getId()).name(), x, y, color, shadow)
                : original.call(instance, textRenderer, text, x, y, color, shadow);
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/util/DrawingUtil;drawWrappedString(Lnet/minecraft/client/gui/DrawContext;" +
                            "Ljava/lang/String;IIIII)V",
                    ordinal = 0
            )
    )
    private void replaceSummary(DrawContext context, String string, int x, int y, int wrapWidth, int lines, int color, Operation<Void> original) {
        if (ALib.MM_DATA.containsKey(mod.getId()) && client.textRenderer != null) {
            context.drawText(client.textRenderer, ALib.MM_DATA.get(mod.getId()).summary(), x, y, color, true);
            return;
        }

        original.call(context, string, x, y, wrapWidth, lines, color);
    }

    @Inject(method = "render", at = @At("TAIL"))
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

        if (!ALib.MM_DATA.containsKey(modId)) {

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
            AtomicBoolean bl = new AtomicBoolean(false);
            mod.getAuthors().forEach(st -> {
                if (st.contains("AcoYT")) bl.set(true);
            });

            if (bl.get()) {
                drawContext.drawTexture(AcornLib.id("acorn.png"), rowWidth - 5, y, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            // ModMenu Icons
            for (String modIde : ALib.MM_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    drawContext.drawTexture(ALib.MM_ICONS.get(modId), rowWidth - (bl.get() ? 17 : 5), y, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            for (String modIde : ALib.MM_MORE_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    drawContext.drawTexture(ALib.MM_MORE_ICONS.get(modId), rowWidth - 29, y, 0.0F, 0.0F, 16, 16, 16, 16);
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
}
