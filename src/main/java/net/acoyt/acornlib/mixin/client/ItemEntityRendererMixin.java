package net.acoyt.acornlib.mixin.client;

//? if <= 1.21.1 {
/*import com.mojang.blaze3d.vertex.PoseStack;
import net.acoyt.acornlib.api.client.HeldItemPredicate;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemDisplayContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if <= 1.21.1 {
/*@Mixin(value = ItemEntityRenderer.class, priority = 1)
public abstract class ItemEntityRendererMixin {
    @Inject(method = "render(Lnet/minecraft/world/entity/item/ItemEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"))
    private void acornlib$modifyRenderMode(ItemEntity itemEntity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        HeldItemPredicate.currentContext = ItemDisplayContext.GROUND;
    }
}
*///? }