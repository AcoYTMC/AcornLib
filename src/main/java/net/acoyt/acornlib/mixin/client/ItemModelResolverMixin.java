package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.api.item.ModelVaryingItem;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

/**
 * @author AcoYT
 */
@Mixin(ItemModelResolver.class)
public abstract class ItemModelResolverMixin {
    //? if > 1.21.11 {
    /*@Shadow protected abstract ItemModel getItemModel(ResourceLocation modelId);
    *///? } else {
    @Shadow @Final private Function<ResourceLocation, ItemModel> modelGetter;
    //? }

    @Inject(method = "appendItemLayers", at = @At("HEAD"), cancellable = true)
    private void acornlib$blockingItemModels(ItemStackRenderState output, ItemStack item, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed, CallbackInfo ci) {
        if (item != null) {
            if (item.getItem() instanceof ModelVaryingItem varyingItem) {
                ResourceLocation id = varyingItem.getModel(displayContext, item, owner);
                this.getItemModel(id)
                        .update(output, item, (ItemModelResolver) (Object) this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);

                ci.cancel();
            }

            if (item.has(AcornDataComponents.SECONDARY_MODEL)) {
                this.getItemModel(item.getOrDefault(AcornDataComponents.SECONDARY_MODEL, ResourceLocation.withDefaultNamespace("carrot")))
                        .update(output, item, (ItemModelResolver)(Object)this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);
            }

            if (item.has(AcornDataComponents.TERTIARY_MODEL)) {
                this.getItemModel(item.getOrDefault(AcornDataComponents.TERTIARY_MODEL, ResourceLocation.withDefaultNamespace("carrot")))
                        .update(output, item, (ItemModelResolver)(Object)this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);
            }
        }
    }

    //? if <= 1.21.11 {
    @Unique
    private ItemModel getItemModel(ResourceLocation id) {
        return this.modelGetter.apply(id);
    }
    //? }
}
