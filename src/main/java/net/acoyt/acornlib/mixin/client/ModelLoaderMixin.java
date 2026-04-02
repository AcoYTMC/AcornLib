package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.api.item.LayeredModelItem;
import net.acoyt.acornlib.impl.AcornLibClient;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
                    ordinal = 1
            )
    )
    private void acornlib$onInit(CallbackInfo ci) {
        Registries.ITEM.forEach(item -> {
            if (item instanceof LayeredModelItem modelItem) {
                modelItem.getModelsToLoad().forEach(id -> this.loadItemModel(ModelIdentifier.ofInventoryVariant(id)));
                AcornLibClient.models.addAll(modelItem.getModelsToLoad());
            }
        });
    }
}
