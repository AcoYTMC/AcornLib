package net.acoyt.acornlib.api.item;

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
public interface LayeredModelItem {
    //~ if > 1.21.11 '@Nullable LivingEntity entity' -> 'ItemOwner owner'
    List<ResourceLocation> getModels(ItemDisplayContext renderMode, ItemStack stack, @Nullable LivingEntity entity);
    List<ResourceLocation> getModelsToLoad();
}
