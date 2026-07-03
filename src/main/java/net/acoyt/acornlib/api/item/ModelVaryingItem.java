package net.acoyt.acornlib.api.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

/**
 * @author AcoYT
 */
public interface ModelVaryingItem {
    /**
     * @param stack The ItemStack
     * @param displayContext The ItemDisplayContext being rendered in
     * @param context The context, contains the entity and stuff
     * @return The location of the model to use, etc. ResourceLocation.of("gilded", "crystalline_longsword_blocking");
     */
    ResourceLocation getModel(ItemStack stack, ItemDisplayContext displayContext, ItemOwner context);
}
