package net.acoyt.acornlib.api.client;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.Identifier;

public class HeldItemPredicate {
    public static ModelTransformationMode currentItemRenderMode;
    private static final String renderHeld = "is_held";

    public static void registerHeldModelPredicate() {
        ModelPredicateProviderRegistry.register(Identifier.ofVanilla(renderHeld), (stack, world, entity, seed) -> {
            if (currentItemRenderMode == null || MiscUtils.isGui(currentItemRenderMode)) return 0.0F;
            return 1.0F;
        });
    }
}
