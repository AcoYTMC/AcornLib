package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.impl.init.AcornComponents;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public PlayerEntityRendererMixin(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "getArmPose",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void skylight$twoHandedPoses(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<ArmPose> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.contains(AcornComponents.TWO_HANDED)) {
            cir.setReturnValue(ArmPose.CROSSBOW_CHARGE);
        }

        if (stack.contains(AcornComponents.FOLLOWS_CAM)) {
            cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
        }
    }
}