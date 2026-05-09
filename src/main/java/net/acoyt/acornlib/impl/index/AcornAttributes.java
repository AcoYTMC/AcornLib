package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.AttributeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author AcoYT
 */
public interface AcornAttributes {
    AttributeRegistrant ATTRIBUTES = new AttributeRegistrant(AcornLib.MOD_ID);

    RegistryEntry<EntityAttribute> OPACITY = ATTRIBUTES.registerRef(
            "player.opacity",
            new ClampedEntityAttribute("attribute.name.player.opacity", 1.0, 0.0, 1.0)
                    .setTracked(true).setCategory(EntityAttribute.Category.NEUTRAL)
    );

    static void init() {}
}
