package net.acoyt.acornlib.api.client;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.List;

public class HeldItemPredicate {
    public static ModelTransformationMode currentItemRenderMode;
    private static final String renderHeld = "is_held";

    private static final List<ModelTransformationMode> renderModeHands = Arrays.asList(
            ModelTransformationMode.FIRST_PERSON_LEFT_HAND,
            ModelTransformationMode.FIRST_PERSON_RIGHT_HAND,
            ModelTransformationMode.THIRD_PERSON_LEFT_HAND,
            ModelTransformationMode.THIRD_PERSON_RIGHT_HAND
    );

    public static void registerHeldModelPredicate() {
        ModelPredicateProviderRegistry.register(Identifier.ofVanilla(renderHeld), (stack, world, entity, seed) -> {
            if (currentItemRenderMode == null || currentItemRenderMode == ModelTransformationMode.FIXED) return 0.0F;
            return 1.0F;
        });
    }
}
