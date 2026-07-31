package net.acoyt.acornlib.api.client;

//? if <= 1.21.1 {
/*import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
*///? }

/**
 * @author AcoYT
 */
//? if <= 1.21.1 {
/*public class HeldItemPredicate {
    public static ItemDisplayContext currentContext;
    private static final String isHeld = "is_held";

    public static void init() {
        ItemProperties.registerGeneric(Identifier.withDefaultNamespace(isHeld), (stack, level, entity, seed) -> {
            if (currentContext == null || MiscUtils.isGui(currentContext)) return 0.0F;
            return 1.0F;
        });
    }
}
*///? }