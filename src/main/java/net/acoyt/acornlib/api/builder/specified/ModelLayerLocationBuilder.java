package net.acoyt.acornlib.api.builder.specified;

import net.acoyt.acornlib.api.builder.SpecifiedBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

import java.util.function.BiConsumer;
import java.util.function.Function;

//? if > 1.21.11 {
/*import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry.TexturedLayerDefinitionProvider;
*///? } else {
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
//? }

/**
 * @author AcoYT
 */
@Environment(EnvType.CLIENT)
//? if > 1.21.11 {
/*public class ModelLayerLocationBuilder extends SpecifiedBuilder<ModelLayerLocation, TexturedLayerDefinitionProvider> {
 *///? } else {
public class ModelLayerLocationBuilder extends SpecifiedBuilder<ModelLayerLocation, EntityModelLayerRegistry.TexturedModelDataProvider> {
    //? }
    public ModelLayerLocationBuilder(String modId) {
        super(modId);
    }

    public Function<Identifier, ModelLayerLocation> applyFunction() {
        return id -> new ModelLayerLocation(id, "main");
    }

    //? if > 1.21.11 {
    /*public BiConsumer<ModelLayerLocation, TexturedLayerDefinitionProvider> endFunction() {
        return ModelLayerRegistry::registerModelLayer;
    }
    *///? } else {
    public BiConsumer<ModelLayerLocation, EntityModelLayerRegistry.TexturedModelDataProvider> endFunction() {
        return EntityModelLayerRegistry::registerModelLayer;
    }
    //? }
}
