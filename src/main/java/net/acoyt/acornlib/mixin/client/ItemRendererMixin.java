package net.acoyt.acornlib.mixin.client;

//? if <= 1.21.1 {
/*import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.acoyt.acornlib.api.item.LayeredModelItem;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
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
/*@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow @Final private ItemModelShaper itemModelShaper;

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
    private void acornlib$render(ItemRenderer instance, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, BakedModel bakedModel, Operation<Void> original,
                                 @Local(argsOnly = true) LivingEntity entity) {
        if (stack.getItem() instanceof LayeredModelItem modelItem) {
            modelItem.getModels(context, stack, entity)
                    .forEach(id -> original.call(instance, stack, context, leftHanded, poseStack, bufferSource, light, overlay, this.itemModelShaper.getModelManager().getModel(ModelIdentifier.inventory(id))));

            return;
        }

        original.call(instance, stack, context, leftHanded, poseStack, bufferSource, light, overlay, bakedModel);
    }

    @Inject(
            method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;" +
                    "Lnet/minecraft/world/item/ItemStack;" +
                    "Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;" +
                    "Lnet/minecraft/client/renderer/MultiBufferSource;" +
                    "Lnet/minecraft/world/level/Level;III)V",
            at = @At("HEAD")
    )
    private void acornlib$storeRenderMode(LivingEntity entity, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, Level level, int light, int overlay, int seed, CallbackInfo ci) {
        HeldItemPredicate.currentContext = context;
    }

    @Inject(
            method = "render",
            at = @At("HEAD")
    )
    private void acornlib$resetRenderMode(ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, BakedModel bakedModel, CallbackInfo ci) {
        HeldItemPredicate.currentContext = null;
    }
}
*///? }