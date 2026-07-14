package net.acoyt.acornlib.mixin.access;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.client.addon.AvatarRenderStateAddon;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*@Mixin(AvatarRenderState.class)
public abstract class AvatarRenderStateMixin implements AvatarRenderStateAddon.Getter {
    @Unique private final AvatarRenderStateAddon acornlib$stateAddon = new AvatarRenderStateAddon();

    @Override
    public AvatarRenderStateAddon acornlib$getStateAddon() {
        return this.acornlib$stateAddon;
    }
}
*///? }