package net.acoyt.acornlib.mixin.client;

import net.acoyt.acornlib.impl.index.AcornDataComponents;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author AcoYT
 */
@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin<AvatarlikeEntity extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<AvatarlikeEntity, AvatarRenderState, PlayerModel> {
    public AvatarRendererMixin(EntityRendererProvider.Context ctx, PlayerModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void acornlib$twoHandedPoses(Avatar avatar, ItemStack itemInHand, InteractionHand hand, CallbackInfoReturnable<ArmPose> cir) {
        if (itemInHand.has(AcornDataComponents.TWO_HANDED)) {
            cir.setReturnValue(ArmPose.CROSSBOW_CHARGE);
        }

        if (itemInHand.has(AcornDataComponents.FOLLOWS_CAM)) {
            cir.setReturnValue(ArmPose.CROSSBOW_HOLD);
        }
    }
}
