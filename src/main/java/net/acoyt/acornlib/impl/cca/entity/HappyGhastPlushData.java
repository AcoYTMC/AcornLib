package net.acoyt.acornlib.impl.cca.entity;

//? if > 1.21.5 {
/*import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.5 {
/*public class HappyGhastPlushData implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<HappyGhastPlushData> KEY = ComponentRegistry.getOrCreate(AcornLib.id("happy_ghast_plush"), HappyGhastPlushData.class);
    private final HappyGhast happyGhast;
    public ItemStack plushStack = ItemStack.EMPTY;
    public int fallback = 0;

    public HappyGhastPlushData(HappyGhast happyGhast) {
        this.happyGhast = happyGhast;
    }

    public void sync() {
        KEY.sync(this.happyGhast);
    }

    public void tick() {
        if (this.fallback > 0) {
            this.fallback--;
            if (this.fallback == 0) {
                this.sync();
            }
        }
    }

    public void readData(ValueInput view) {
        this.plushStack = view.read("PlushStack", ItemStack.CODEC).orElse(ItemStack.EMPTY);
        this.fallback = view.getIntOr("Fallback", 0);
    }

    public void writeData(ValueOutput view) {
        if (!this.plushStack.isEmpty()) {
            view.store("PlushStack", ItemStack.CODEC, this.plushStack);
        }

        view.putInt("Fallback", this.fallback);
    }
}
*///? }