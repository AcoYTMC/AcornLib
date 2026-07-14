package net.acoyt.acornlib.impl.client.addon;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.cca.entity.HappyGhastPlushData;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*public class HappyGhastRenderStateAddon {
    public ItemStack plushStack = ItemStack.EMPTY;
    @Nullable public Level level = null;
    public HappyGhast entity;

    public void extract(HappyGhast entity, HappyGhastRenderState state, float tickProgress) {
        this.plushStack = HappyGhastPlushData.KEY.get(entity).plushStack;
        this.level = entity.level();
        this.entity = entity;
    }

    public static HappyGhastRenderStateAddon get(HappyGhastRenderState state) {
        return ((Getter) state).acornlib$getStateAddon();
    }

    public interface Getter {
        HappyGhastRenderStateAddon acornlib$getStateAddon();
    }
}
*///? }