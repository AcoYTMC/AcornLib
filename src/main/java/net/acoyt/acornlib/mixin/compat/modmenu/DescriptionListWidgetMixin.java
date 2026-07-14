package net.acoyt.acornlib.mixin.compat.modmenu;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.terraformersmc.modmenu.gui.widget.DescriptionListWidget;
import com.terraformersmc.modmenu.util.mod.Mod;
import net.acoyt.acornlib.api.ALib;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author AcoYT
 */
@Mixin(DescriptionListWidget.class)
public abstract class DescriptionListWidgetMixin {
    @WrapOperation(
            method = "rebuildUI",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/terraformersmc/modmenu/util/mod/Mod;getFormattedDescription()Lnet/minecraft/network/chat/Component;"
            )
    )
    private Component acornlib$replaceDescription(Mod instance, Operation<Component> original) {
        return ALib.MM_DATA.containsKey(instance.getId()) ? ALib.MM_DATA.get(instance.getId()).description() : original.call(instance);
    }
}
