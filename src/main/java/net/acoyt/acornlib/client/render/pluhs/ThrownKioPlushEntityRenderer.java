package net.acoyt.acornlib.client.render.pluhs;

import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.init.AcornBlocks;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ThrownKioPlushEntityRenderer extends ThrownPlushEntityRenderer {
    public ThrownKioPlushEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AcornBlocks.KIO_PLUSH.asItem().getDefaultStack());
    }
}
