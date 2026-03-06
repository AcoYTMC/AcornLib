package net.acoyt.acornlib.mixin.compat.modmenu;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.gui.widget.DescriptionListWidget;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DescriptionListWidget.class)
public abstract class DescriptionListWidgetMixin {
    @WrapOperation(
            method = "renderList",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/util/mod/Mod;getFormattedDescription()Lnet/minecraft/text/Text;"
            )
    )
    private Text replaceDescription(Mod instance, Operation<Text> original) {
        return ALib.MM_DATA.containsKey(instance.getId()) ? ALib.MM_DATA.get(instance.getId()).description() : original.call(instance);
    }
}
