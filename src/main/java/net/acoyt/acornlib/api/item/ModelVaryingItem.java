package net.acoyt.acornlib.api.item;

//~ if > 1.21.11 '@Nullable LivingEntity entity' -> 'ItemOwner owner' {
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.List;

//? if > 1.21.11 {
/*import net.minecraft.world.entity.ItemOwner;
*///? } else {
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
//? }

/**
 * @author AcoYT
 */
public interface ModelVaryingItem extends LayeredModelItem {
    ResourceLocation getModel(ItemDisplayContext renderMode, ItemStack stack, @Nullable LivingEntity entity);

    default List<ResourceLocation> getModels(ItemDisplayContext renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        //~ if > 1.21.11 'entity' -> 'owner'
        return List.of(getModel(renderMode, stack, entity));
    }
}
//~ }