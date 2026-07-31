package net.acoyt.acornlib.api.item;

//~ if > 1.21.1 '@Nullable LivingEntity entity' -> 'ItemOwner owner' {
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;

//? if > 1.21.1 {
import net.minecraft.world.entity.ItemOwner;
//? } else {
/*import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
*///? }

/**
 * @author AcoYT
 */
public interface ModelVaryingItem extends LayeredModelItem {
    Identifier getModel(ItemDisplayContext renderMode, ItemStack stack, ItemOwner owner);

    default List<Identifier> getModels(ItemDisplayContext renderMode, ItemStack stack, ItemOwner owner) {
        //~ if > 1.21.1 'entity' -> 'owner'
        return List.of(getModel(renderMode, stack, owner));
    }
}
//~ }