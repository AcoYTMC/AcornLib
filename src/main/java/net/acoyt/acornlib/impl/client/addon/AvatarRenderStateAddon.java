package net.acoyt.acornlib.impl.client.addon;

//? if > 1.21.8 {
/*import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.Avatar;
*///? } else if > 1.21.4 {
/*import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.world.entity.player.Player;
*///? }

//? if > 1.21.4 {
/*import net.acoyt.acornlib.impl.index.AcornAttributes;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.4 {
/*public class AvatarRenderStateAddon {
    public double opacity = 1.0;
    //? if > 1.21.8 {
    /^public Avatar entity;

    public void extract(Avatar entity, AvatarRenderState state, float tickProgress) {
        this.opacity = entity.getAttributes().hasAttribute(AcornAttributes.OPACITY) ? entity.getAttributeValue(AcornAttributes.OPACITY) : 1.0;
        this.entity = entity;
    }

    public static AvatarRenderStateAddon get(AvatarRenderState state) {
        return ((Getter) state).acornlib$getStateAddon();
    }
    ^///? } else {
    public Player entity;

    public void extract(Player player, PlayerRenderState, float tickProgress) {
        this.opacity = entity.getAttributes().hasAttribute(AcornAttributes.OPACITY) ? entity.getAttributeValue(AcornAttributes.OPACITY) : 1.0;
        this.entity = entity;
    }

    public static AvatarRenderStateAddon get(PlayerRenderState state) {
        return ((Getter) state).acornlib$getStateAddon();
    }
    //? }

    public interface Getter {
        AvatarRenderStateAddon acornlib$getStateAddon();
    }
}
*///? }