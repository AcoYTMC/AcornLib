package net.acoyt.acornlib.api.item;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.item.ItemStack;

public interface CustomGlintItem {
    VertexConsumer getConsumer(ItemStack stack, ModelTransformationMode renderMode, VertexConsumerProvider provider);
}
