package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.AvatarRenderStateAddon;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin<AvatarLikeEntity extends Avatar & ClientAvatarEntity> extends LivingEntityRenderer<AvatarLikeEntity, AvatarRenderState, PlayerModel> {
    public AvatarRendererMixin(EntityRendererProvider.Context ctx, PlayerModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V",
            at = @At("HEAD")
    )
    private void acornlib$extractStateAddon(AvatarLikeEntity entity, AvatarRenderState state, float partialTicks, CallbackInfo ci) {
        AvatarRenderStateAddon.get(state).extract(entity, state, partialTicks);
    }
}
*///? }