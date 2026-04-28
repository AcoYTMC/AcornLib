package net.acoyt.acornlib.api.item;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author AcoYT
 */
public interface ModelVaryingItem extends LayeredModelItem {
    Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity);

    default List<Identifier> getModels(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity) {
        return List.of(getModel(renderMode, stack, entity));
    }
}
