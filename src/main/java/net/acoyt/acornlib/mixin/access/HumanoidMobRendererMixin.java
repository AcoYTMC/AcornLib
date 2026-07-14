package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.HumanoidRenderStateAddon;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(HumanoidMobRenderer.class)
public abstract class HumanoidMobRendererMixin<T extends Mob, S extends HumanoidRenderState, M extends HumanoidModel<S>> {
    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;F)V",
            at = @At("HEAD")
    )
    private void acornlib$updateBipedStateAccess(T entity, S state, float partialTicks, CallbackInfo ci) {
        HumanoidRenderStateAddon.get(state).extract(entity, state, partialTicks);
    }
}
*///? }