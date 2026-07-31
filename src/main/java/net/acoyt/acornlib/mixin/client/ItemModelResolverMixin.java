package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.api.item.LayeredModelItem;
import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

//? if > 1.21.1 {
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;
//? } else {
/*import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.world.entity.LivingEntity;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.1 {
@Mixin(ItemModelResolver.class)
public abstract class ItemModelResolverMixin {
    //? if > 1.21.11 {
    @Shadow protected abstract ItemModel getItemModel(Identifier modelId);
    //? } else {
    /*@Shadow @Final private Function<Identifier, ItemModel> modelGetter;
    *///? }

    @Inject(method = "appendItemLayers", at = @At("HEAD"), cancellable = true)
    private void acornlib$blockingItemModels(ItemStackRenderState output, ItemStack item, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed, CallbackInfo ci) {
        if (item != null) {
            boolean cancelled = false;

            if (item.getItem() instanceof LayeredModelItem modelItem) {
                for (Identifier id : modelItem.getModels(displayContext, item, owner)) {
                    this.getItemModel(id)
                            .update(output, item, (ItemModelResolver) (Object) this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);
                }

                cancelled = true;
            }

            if (item.has(AcornDataComponents.SECONDARY_MODEL)) {
                this.getItemModel(item.getOrDefault(AcornDataComponents.SECONDARY_MODEL, Identifier.withDefaultNamespace("carrot")))
                        .update(output, item, (ItemModelResolver)(Object)this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);
            }

            if (item.has(AcornDataComponents.TERTIARY_MODEL)) {
                this.getItemModel(item.getOrDefault(AcornDataComponents.TERTIARY_MODEL, Identifier.withDefaultNamespace("carrot")))
                        .update(output, item, (ItemModelResolver)(Object)this, displayContext, level instanceof ClientLevel clientWorld ? clientWorld : null, owner, seed);
            }

            if (cancelled) ci.cancel();
        }
    }

    //? if <= 1.21.11 {
    /*@Unique
    private ItemModel getItemModel(Identifier id) {
        return this.modelGetter.apply(id);
    }
    *///? }
}
//? } else {
/*@Mixin(ItemRenderer.class)
public abstract class ItemModelResolverMixin {
    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @WrapOperation(
            method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;" +
                    "Lnet/minecraft/world/item/ItemStack;" +
                    "Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;" +
                    "Lnet/minecraft/client/renderer/MultiBufferSource;" +
                    "Lnet/minecraft/world/level/Level;III)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;render(Lnet/minecraft/world/item/ItemStack;" +
                            "Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V"
            )
    )
    private void acornlib$renderItem(ItemRenderer instance, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int lightCoords, int overlayCoords, BakedModel bakedModel, Operation<Void> original,
                                     LivingEntity entity) {
        boolean returned = false;

        if (stack.getItem() instanceof LayeredModelItem modelItem) {
            for (Identifier location : modelItem.getModels(context, stack, entity)) {
                original.call(
                        instance, stack,
                        context, leftHanded,
                        poseStack, bufferSource,
                        lightCoords, overlayCoords,
                        this.itemModelShaper.getModelManager().getModel(ModelIdentifier.inventory(location))
                );
            }

            returned = true;
        }

        if (stack.has(AcornDataComponents.SECONDARY_MODEL)) {
            Identifier secondary = stack.getOrDefault(AcornDataComponents.SECONDARY_MODEL, Identifier.withDefaultNamespace("carrot"));

            original.call(
                    instance, stack,
                    context, leftHanded,
                    poseStack, bufferSource,
                    lightCoords, overlayCoords,
                    this.itemModelShaper.getModelManager().getModel(ModelIdentifier.inventory(secondary))
            );
        }

        if (stack.has(AcornDataComponents.TERTIARY_MODEL)) {
            Identifier tertiary = stack.getOrDefault(AcornDataComponents.TERTIARY_MODEL, Identifier.withDefaultNamespace("carrot"));

            original.call(
                    instance, stack,
                    context, leftHanded,
                    poseStack, bufferSource,
                    lightCoords, overlayCoords,
                    this.itemModelShaper.getModelManager().getModel(ModelIdentifier.inventory(tertiary))
            );
        }

        if (!returned) {
            original.call(
                    instance, stack,
                    context, leftHanded,
                    poseStack, bufferSource,
                    lightCoords, overlayCoords,
                    bakedModel
            );
        }
    }
}
*///? }