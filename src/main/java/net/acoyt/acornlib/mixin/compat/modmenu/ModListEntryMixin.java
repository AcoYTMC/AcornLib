package net.acoyt.acornlib.mixin.compat.modmenu;

//~ if > 1.21.11 'drawString(' -> 'text(' {

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.config.ModMenuConfig;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicBoolean;

//? if > 1.21.10 {
import net.minecraft.client.renderer.RenderPipelines;
//? }

/**
 * @author AcoYT
 */
@Mixin(ModListEntry.class)
public abstract class ModListEntryMixin extends ObjectSelectionList.Entry<ModListEntry> {
    @Shadow(remap = false) @Final public Mod mod;
    @Shadow @Final protected Minecraft client;

    //? if > 1.21.10 {
    //? } else {
    /*@Shadow public abstract void render(GuiGraphicsExtractor DrawContext, int index, int y, int x, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float delta);
    *///? }

    //? if > 1.21.1 {
    @Shadow public abstract int getXOffset();
    @Shadow public abstract int getYOffset();

    @WrapOperation(
            //~ if > 1.21.11 'renderContent' -> 'extractContent'
            method = "extractContent",
            at = @At(
                    value = "INVOKE",
                    //~ if > 1.21.11 'drawString' -> 'text'
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)V"
            )
    )
    private void acornlib$replaceName(GuiGraphicsExtractor instance, Font font, FormattedCharSequence str, int x, int y, int color, Operation<Void> original) {
        if (ALib.MM_DATA.containsKey(mod.getId())) {
            instance.text(font, ALib.MM_DATA.get(mod.getId()).name(), x, y, color);
            return;
        }

        original.call(instance, font, str, x, y, color);
    }

    @WrapOperation(
            //~ if > 1.21.11 'renderContent' -> 'extractContent'
            method = "extractContent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/util/DrawingUtil;drawWrappedString(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Ljava/lang/String;IIIII)V",
                    ordinal = 0
            )
    )
    private void acornlib$replaceSummary(GuiGraphicsExtractor graphics, String string, int x, int y, int wrapWidth, int lines, int color, Operation<Void> original) {
        if (ALib.MM_DATA.containsKey(mod.getId())) {
            graphics.text(client.font, ALib.MM_DATA.get(mod.getId()).summary(), x, y, color, true);
            return;
        }

        original.call(graphics, string, x, y, wrapWidth, lines, color);
    }

    //~ if > 1.21.11 'renderContent' -> 'extractContent'
    @Inject(method = "extractContent", at = @At("TAIL"))
    private void acornlib$modifyModNameColor(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float delta, CallbackInfo ci) {
        int x = this.getX() + this.getXOffset();
        int y = this.getContentY() + this.getYOffset();
        int rowWidth = this.getContentWidth();
        int iconSize = ModMenuConfig.COMPACT_LIST.getValue() ? 19 : 32;
        String modId = mod.getId();

        Component name = Component.literal(mod.getTranslatedName());
        FormattedText trimmedName = name;
        int maxNameWidth = rowWidth - iconSize - 3;
        Font font = this.client.font;
        if (font.width(name) > maxNameWidth) {
            FormattedText ellipsis = FormattedText.of("...");
            trimmedName = FormattedText.composite(font.substrByWidth(name, maxNameWidth - font.width(ellipsis)), ellipsis);
        }

        if (!ALib.MM_DATA.containsKey(modId)) {

            // ModIds
            if (AcornLib.isMidnightLibLoaded) {
                if (AcornConfig.displayModIds) {
                    graphics.text(font, modId, x + 146, y + 1, ALib.MMM.getOrDefault(modId, 0xFFFFFFFF));
                }
            }

            // ModMenu Color Set
            for (String modIde : ALib.MMM.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, ALib.MMM.get(modId));
                }
            }

            // AcoYT Badge
            AtomicBoolean bl = new AtomicBoolean(false);
            mod.getAuthors().forEach(st -> {
                if (st.contains("AcoYT")) bl.set(true);
            });

            if (bl.get()) {
                graphics.blit(RenderPipelines.GUI_TEXTURED, AcornLib.id("acorn.png"), rowWidth - 5, y, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            // ModMenu Icons
            for (String modIde : ALib.MM_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.blit(RenderPipelines.GUI_TEXTURED, ALib.MM_ICONS.get(modId), rowWidth - (bl.get() ? 17 : 5), y, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            for (String modIde : ALib.MM_MORE_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.blit(RenderPipelines.GUI_TEXTURED, ALib.MM_MORE_ICONS.get(modId), rowWidth - 29, y, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            // Builtin ModMenu Compat Colors
            if (AcornLib.isMidnightLibLoaded) {
                if (AcornConfig.nameColorCompat) {
                    for (String modIde : NameColorList.SPECIAL_MMM.keySet()) {
                        if (modIde.equals(modId)) {
                            graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.SPECIAL_MMM.get(modId));
                        }
                    }
                }
            }

            // Author-Specific
            for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
                if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(modId) && !ALib.MMM.containsKey(modId)) {
                    graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.AUTHOR_SPECIFIC.get(author));
                }
            }
        }
    }
    //? } else {
    /*@WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I"
            )
    )
    private int acornlib$replaceName(GuiGraphicsExtractor instance, Font font, FormattedCharSequence text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        return ALib.MM_DATA.containsKey(mod.getId())
                ? instance.text(font, ALib.MM_DATA.get(mod.getId()).name(), x, y, color, shadow)
                : original.call(instance, font, text, x, y, color, shadow);
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/util/DrawingUtil;drawWrappedString(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Ljava/lang/String;IIIII)V",
                    ordinal = 0
            )
    )
    private void acornlib$replaceSummary(GuiGraphicsExtractor graphics, String string, int x, int y, int wrapWidth, int lines, int color, Operation<Void> original) {
        if (ALib.MM_DATA.containsKey(mod.getId())) {
            graphics.text(client.font, ALib.MM_DATA.get(mod.getId()).summary(), x, y, color, true);
            return;
        }

        original.call(graphics, string, x, y, wrapWidth, lines, color);
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void acornlib$modifyModNameColor(GuiGraphicsExtractor graphics, int index, int y, int x, int rowWidth, int rowHeight, int mouseX, int mouseY, boolean hovered, float delta, CallbackInfo ci) {
        String modId = mod.getId();
        int iconSize = ModMenuConfig.COMPACT_LIST.getValue() ? 19 : 32;

        Component name = Component.literal(mod.getTranslatedName());
        FormattedText trimmedName = name;
        int maxNameWidth = rowWidth - iconSize - 3;
        Font font = client.font;
        if (font.width(name) > maxNameWidth) {
            FormattedText ellipsis = FormattedText.of("...");
            trimmedName = FormattedText.composite(font.substrByWidth(name, maxNameWidth - font.width(ellipsis)), ellipsis);
        }

        if (!ALib.MM_DATA.containsKey(mod.getId())) {

            // ModIds
            if (AcornLib.isMidnightLibLoaded && AcornConfig.displayModIds) {
                graphics.text(font, modId, x + 146, y + 1, ALib.MMM.getOrDefault(modId, 0xFFFFFFFF));
            }

            // ModMenu Color Set
            for (String modIde : ALib.MMM.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, ALib.MMM.get(modId));
                }
            }

            // AcoYT Badge
            AtomicBoolean bl = new AtomicBoolean(false);
            mod.getAuthors().forEach(st -> {
                if (st.contains("AcoYT")) bl.set(true);
            });

            if (bl.get()) {
                graphics.blit(AcornLib.id("acorn.png"), rowWidth - 5, y, 0.0F, 0.0F, 16, 16, 16, 16);
            }

            // ModMenu Icons
            for (String modIde : ALib.MM_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.blit(ALib.MM_ICONS.get(modId), rowWidth - (bl.get() ? 17 : 5), y, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            for (String modIde : ALib.MM_MORE_ICONS.keySet()) {
                if (modIde.equals(modId)) {
                    graphics.blit(ALib.MM_MORE_ICONS.get(modId), rowWidth - 29, y, 0.0F, 0.0F, 16, 16, 16, 16);
                }
            }

            // Builtin ModMenu Compat Colors
            if (AcornLib.isMidnightLibLoaded && AcornConfig.nameColorCompat) {
                for (String modIde : NameColorList.SPECIAL_MMM.keySet()) {
                    if (modIde.equals(modId)) {
                        graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.SPECIAL_MMM.get(modId));
                    }
                }
            }

            // Author-Specific
            for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
                if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(modId) && !ALib.MMM.containsKey(modId)) {
                    graphics.text(font, Language.getInstance().getVisualOrder(trimmedName), x + iconSize + 3, y + 1, NameColorList.AUTHOR_SPECIFIC.get(author));
                }
            }
        }
    }
    *///? }
}
//~ }