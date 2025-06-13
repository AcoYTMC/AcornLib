package net.acoyt.acornlib.client.render.pluhs;

import net.acoyt.acornlib.client.render.ThrownPlushEntityRenderer;
import net.acoyt.acornlib.init.AcornBlocks;
import net.minecraft.client.render.entity.EntityRendererFactory;

public class ThrownMythoricalPlushEntityRenderer extends ThrownPlushEntityRenderer {
    public ThrownMythoricalPlushEntityRenderer(EntityRendererFactory.Context context) {
        super(context, AcornBlocks.MYTHORICAL_PLUSH.asItem().getDefaultStack());
    }
}
