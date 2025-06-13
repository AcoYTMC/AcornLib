package net.acoyt.acornlib.client.render.pluhs;

import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.init.AcornBlocks;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ThrownClownAcoPlushEntityRenderer extends ThrownPlushEntityRenderer {
    public ThrownClownAcoPlushEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AcornBlocks.CLOWN_ACO_PLUSH.asItem().getDefaultStack());
    }
}
