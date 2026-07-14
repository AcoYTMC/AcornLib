package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.HappyGhastRenderStateAddon;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(HappyGhastRenderState.class)
public class HappyGhastRenderStateMixin implements HappyGhastRenderStateAddon.Getter {
    @Unique private final HappyGhastRenderStateAddon acornlib$stateAddon = new HappyGhastRenderStateAddon();

    @Override
    public HappyGhastRenderStateAddon acornlib$getStateAddon() {
        return this.acornlib$stateAddon;
    }
}
*///? }