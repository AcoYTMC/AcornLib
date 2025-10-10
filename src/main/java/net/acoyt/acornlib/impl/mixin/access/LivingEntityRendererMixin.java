package net.acoyt.acornlib.impl.mixin.access;

import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityModelAccess;
import net.acoyt.acornlib.impl.util.interfaces.PlayerEntityRenderStateAccess;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>>
        extends EntityRenderer<T, S> {

    @Shadow
    protected M model;

    public LivingEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At("HEAD")
    )
    private void updateRenderState(T livingEntity, S renderState, float f, CallbackInfo ci) {
        if (renderState instanceof PlayerEntityRenderState state && this.model instanceof PlayerEntityModel playerModel) {
            if (playerModel instanceof PlayerEntityModelAccess modelAccess) {
                modelAccess.acornLib$setRenderState(state);
            }
        }
    }
}
