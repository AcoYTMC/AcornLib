package net.acoyt.acornlib.api.builder.specified;

//? if > 1.21.11 {
/*import net.acoyt.acornlib.api.builder.SpecifiedBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry.TexturedLayerDefinitionProvider;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;
*///? }

/**
 * @author AcoYT
 */
//? if > 1.21.11 {
/*@Environment(EnvType.CLIENT)
public class ModelLayerLocationBuilder extends SpecifiedBuilder<ModelLayerLocation, TexturedLayerDefinitionProvider> {
    public ModelLayerLocationBuilder(String modId) {
        super(modId);
    }

    public Function<ResourceLocation, ModelLayerLocation> applyFunction() {
        return id -> new ModelLayerLocation(id, "main");
    }

    public BiConsumer<ModelLayerLocation, TexturedLayerDefinitionProvider> endFunction() {
        return ModelLayerRegistry::registerModelLayer;
    }
}
*///? }