package net.acoyt.acornlib.impl.index.client;

import net.acoyt.acornlib.api.builder.specified.ModelLayerLocationBuilder;
import net.acoyt.acornlib.impl.AcornLib;
import net.acoyt.acornlib.impl.client.model.PlaneModel;
import net.minecraft.client.model.geom.ModelLayerLocation;

/**
 * @author AcoYT
 */
public interface AcornModelLayerLocations {
    ModelLayerLocationBuilder BUILDER = new ModelLayerLocationBuilder(AcornLib.MOD_ID);

    ModelLayerLocation PLANE = BUILDER.register("plane", PlaneModel::getLayerDefinition);

    static void init() {
        BUILDER.build();
    }
}
