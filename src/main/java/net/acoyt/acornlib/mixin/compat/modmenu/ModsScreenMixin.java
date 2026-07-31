package net.acoyt.acornlib.mixin.compat.modmenu;

//~ if > 1.21.11 'drawString(' -> 'text(' {
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.gui.ModsScreen;
import com.terraformersmc.modmenu.gui.widget.entries.ModListEntry;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.acoyt.acornlib.compat.AcornConfig;
import net.acoyt.acornlib.compat.NameColorList;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author AcoYT
 */
@Mixin(ModsScreen.class)
public abstract class ModsScreenMixin extends Screen {
    @Shadow(remap = false) private ModListEntry selected;
    @Shadow(remap = false) private int rightPaneX;

    protected ModsScreenMixin(Component title) {
        super(title);
    }

    @WrapOperation(
            //~ if > 1.21.11 'render' -> 'extractRenderState'
            method = "extractRenderState",
            at = @At(
                    value = "INVOKE",
                    //~ if > 1.21.11 'drawString' -> 'text'
                    //~ if > 1.21.1 ')I' -> ')V'
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)V",
                    ordinal = 6
            )
    )
    //~ if > 1.21.1 'private int' -> 'private void'
    private void acornlib$replaceName(GuiGraphicsExtractor instance, Font font, FormattedCharSequence text, int x, int y, int color, boolean shadow, Operation<Integer> original) {
        Mod mod = this.selected.getMod();
        if (ALib.MM_DATA.containsKey(mod.getId())) {
            //? if > 1.21.1 {
            instance.text(font, ALib.MM_DATA.get(mod.getId()).name(), x, y, color, shadow);
            return;
            //? } else {
            /*return instance.text(font, ALib.MM_DATA.get(mod.getId()).name(), x, y, color, shadow);
            *///? }
        }

        //? if <= 1.21.1
        //return
        original.call(instance, font, text, x, y, color, shadow);
    }

    //~ if > 1.21.11 'render' -> 'extractRenderState'
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    public void acornlib$render(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
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

            if (!ALib.MM_DATA.containsKey(mod.getId())) {

                // ModMenu Color Set
                for (String modId : ALib.MMM.keySet()) {
                    if (modId.equals(mod.getId())) {
                        graphics.text(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, ALib.MMM.get(modId));
                    }
                }

                // Builtin ModMenu Compat Colors
                if (AcornLib.isMidnightLibLoaded) {
                    if (AcornConfig.nameColorCompat) {
                        for (String modId : NameColorList.SPECIAL_MMM.keySet()) {
                            if (modId.equals(mod.getId())) {
                                graphics.text(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, NameColorList.SPECIAL_MMM.get(modId));
                            }
                        }
                    }
                }

                // Author-Specific
                for (String author : NameColorList.AUTHOR_SPECIFIC.keySet()) {
                    if (mod.getAuthors().contains(author) && !NameColorList.SPECIAL_MMM.containsKey(mod.getId()) && !ALib.MMM.containsKey(mod.getId())) {
                        graphics.text(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, NameColorList.AUTHOR_SPECIFIC.get(author));
                    }
                }

                // Starts-with / Prefixes
                if (AcornLib.isMidnightLibLoaded) {
                    if (AcornConfig.nameColorCompat) {
                        for (String prefix : NameColorList.STARTS_WITH.keySet()) {
                            if (mod.getId().startsWith(prefix)) {
                                graphics.text(this.font, Language.getInstance().getVisualOrder(trimmedName), x + imageOffset, 49, NameColorList.STARTS_WITH.get(prefix));
                            }
                        }
                    }
                }
            }
        }
    }
}
//~ }