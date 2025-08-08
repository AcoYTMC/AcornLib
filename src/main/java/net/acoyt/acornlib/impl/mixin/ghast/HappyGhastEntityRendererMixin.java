package net.acoyt.acornlib.impl.mixin.ghast;

import net.acoyt.acornlib.impl.client.feature.HappyGhastPlushFeatureRenderer;
import net.acoyt.acornlib.impl.util.interfaces.HappyGhastHolder;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.HappyGhastEntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.HappyGhastEntityModel;
import net.minecraft.client.render.entity.state.HappyGhastEntityRenderState;
import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastEntityRenderer.class)
public abstract class HappyGhastEntityRendererMixin extends LivingEntityRenderer<HappyGhastEntity, HappyGhastEntityRenderState, HappyGhastEntityModel> {
    public HappyGhastEntityRendererMixin(EntityRendererFactory.Context ctx, HappyGhastEntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/passive/HappyGhastEntity;Lnet/minecraft/client/render/entity/state/HappyGhastEntityRenderState;F)V", at = @At("HEAD"))
    private void updateRenderState(HappyGhastEntity happyGhast, HappyGhastEntityRenderState renderState, float f, CallbackInfo ci) {
        if (renderState instanceof HappyGhastHolder holder) {
            holder.acornLib$setHappyGhast(happyGhast);
        }
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    private void addCustomFeature(EntityRendererFactory.Context context, CallbackInfo ci) {
        HappyGhastEntityRenderState renderState = this.createRenderState();
        if (renderState instanceof HappyGhastHolder) {
            this.addFeature(new HappyGhastPlushFeatureRenderer<>(this));
        }
    }
}
