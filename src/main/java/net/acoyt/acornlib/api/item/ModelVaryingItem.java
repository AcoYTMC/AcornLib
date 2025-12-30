package net.acoyt.acornlib.api.item;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ModelVaryingItem {
    Identifier getModel(ModelTransformationMode renderMode, ItemStack stack, @Nullable LivingEntity entity);
    List<Identifier> getModelsToLoad();
}
