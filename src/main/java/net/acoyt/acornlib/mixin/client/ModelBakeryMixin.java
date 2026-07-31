package net.acoyt.acornlib.mixin.client;

//? if <= 1.21.1 {
/*import net.acoyt.acornlib.api.item.LayeredModelItem;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if <= 1.21.1 {
/*@Mixin(ModelBakery.class)
public abstract class ModelBakeryMixin {
    @Shadow protected abstract void loadSpecialItemModelAndDependencies(ModelIdentifier par1);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/model/ModelBakery;loadSpecialItemModelAndDependencies(Lnet/minecraft/client/resources/model/ModelIdentifier;)V",
                    ordinal = 1
            )
    )
    private void acornlib$onInit(CallbackInfo ci) {
        BuiltInRegistries.ITEM.forEach(item -> {
            if (item instanceof LayeredModelItem modelItem) {
                modelItem.getModelsToLoad().forEach(id -> this.loadSpecialItemModelAndDependencies(ModelIdentifier.inventory(id)));
            }
        });
    }
}
*///? }