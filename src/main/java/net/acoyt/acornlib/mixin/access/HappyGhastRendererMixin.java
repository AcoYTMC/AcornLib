package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.HappyGhastRenderStateAddon;
import net.acoyt.acornlib.impl.client.layer.HappyGhastPlushLayer;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastRendererMixin extends LivingEntityRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> {
    public HappyGhastRendererMixin(EntityRendererProvider.Context ctx, HappyGhastModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(method = "<init>", at = @At("CTOR_HEAD"))
    private void acornlib$addCustomLayers(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new HappyGhastPlushLayer<>(this));
    }

    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/animal/happyghast/HappyGhast;Lnet/minecraft/client/renderer/entity/state/HappyGhastRenderState;F)V",
            at = @At("HEAD")
    )
    private void acornlib$extractStateAddon(HappyGhast entity, HappyGhastRenderState state, float partialTicks, CallbackInfo ci) {
        HappyGhastRenderStateAddon.get(state).extract(entity, state, partialTicks);
    }
}
*///? }