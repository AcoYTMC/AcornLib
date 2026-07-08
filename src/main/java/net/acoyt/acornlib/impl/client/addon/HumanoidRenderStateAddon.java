package net.acoyt.acornlib.impl.client.addon;

//? if > 1.21.4 {
import net.acoyt.acornlib.impl.index.AcornAttributes;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.Mob;
//? }

/**
 * @author AcoYT
 */
//? if > 1.21.4 {
public class HumanoidRenderStateAddon {
    public double opacity = 1.0;

    public void extract(Mob entity, HumanoidRenderState state, float tickProgress) {
        this.opacity = entity.getAttributes().hasAttribute(AcornAttributes.OPACITY) ? entity.getAttributeValue(AcornAttributes.OPACITY) : 1.0;
    }

    public static HumanoidRenderStateAddon get(HumanoidRenderState state) {
        return ((Getter) state).acornlib$getStateAddon();
    }

    public interface Getter {
        HumanoidRenderStateAddon acornlib$getStateAddon();
    }
}
//? }