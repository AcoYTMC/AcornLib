package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.HumanoidRenderStateAddon;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(HumanoidRenderState.class)
public abstract class HumanoidRenderStateMixin implements HumanoidRenderStateAddon.Getter {
    @Unique private final HumanoidRenderStateAddon acornlib$stateAddon = new HumanoidRenderStateAddon();

    @Override
    public HumanoidRenderStateAddon acornlib$getStateAddon() {
        return this.acornlib$stateAddon;
    }
}
*///? }