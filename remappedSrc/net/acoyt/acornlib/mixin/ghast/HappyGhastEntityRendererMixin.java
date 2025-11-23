package net.acoyt.acornlib.mixin.ghast;

import net.acoyt.acornlib.impl.client.feature.HappyGhastPlushFeatureRenderer;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastHolder;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastEntityRendererMixin extends LivingEntityRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> {
    public HappyGhastEntityRendererMixin(EntityRendererProvider.Context ctx, HappyGhastModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/HappyGhastEntity;Lnet/minecraft/client/render/entity/state/HappyGhastEntityRenderState;F)V", at = @At("HEAD"))
    private void updateRenderState(HappyGhast happyGhast, HappyGhastRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof HappyGhastHolder holder) {
            holder.acornLib$setHappyGhast(happyGhast);
        }
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    private void addCustomFeature(EntityRendererProvider.Context context, CallbackInfo ci) {
        HappyGhastRenderState renderState = this.createRenderState();
        if (renderState instanceof HappyGhastHolder) {
            this.addLayer(new HappyGhastPlushFeatureRenderer<>(this));
        }
    }
}
