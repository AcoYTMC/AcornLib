package net.acoyt.acornlib.client.render.pluhs;

import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.init.AcornBlocks;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ThrownGnarpPlushEntityRenderer extends ThrownPlushEntityRenderer {
    public ThrownGnarpPlushEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AcornBlocks.GNARP_PLUSH.asItem().getDefaultStack());
    }
}
