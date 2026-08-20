package net.acoyt.acornlib.mixin.client;

//? if <= 1.21.1 {
/*import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.api.item.LayeredModelItem;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
*///? }

/**
 * @author AcoYT
 */
//? if <= 1.21.1 {
/*@Mixin(GuiGraphicsExtractor.class)
public class GuiGraphicsExtractorMixin {
    @WrapOperation(
            method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;" +
                    "Lnet/minecraft/world/level/Level;" +
                    "Lnet/minecraft/world/item/ItemStack;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;render(Lnet/minecraft/world/item/ItemStack;" +
                            "Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;" +
                            "Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V"
            )
    )
    private void acornlib$wrapForFix(ItemRenderer instance, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, BakedModel bakedModel, Operation<Void> original, @Local(argsOnly = true) @Nullable LivingEntity entity) {
        if (stack.getItem() instanceof LayeredModelItem modelItem) {
            for (Identifier identifier : modelItem.getModels(context, stack, entity)) {
                original.call(instance, stack, context, leftHanded, poseStack, bufferSource, light, overlay, instance.getItemModelShaper().getModelManager().getModel(ModelIdentifier.inventory(identifier)));
            }

            return;
        }

        original.call(instance, stack, context, leftHanded, poseStack, bufferSource, light, overlay, bakedModel);
    }
}
*///? }