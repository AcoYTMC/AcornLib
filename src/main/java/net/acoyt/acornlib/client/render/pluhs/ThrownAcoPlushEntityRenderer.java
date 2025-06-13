package net.acoyt.acornlib.client.render.pluhs;

import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.init.AcornBlocks;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ThrownAcoPlushEntityRenderer extends ThrownPlushEntityRenderer {
    public ThrownAcoPlushEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AcornBlocks.ACO_PLUSH.asItem().getDefaultStack());
    }
}
