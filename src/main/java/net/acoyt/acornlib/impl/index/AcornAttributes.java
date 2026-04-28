package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

/**
 * @author AcoYT
 */
public interface AcornAttributes {
    RegistryEntry<EntityAttribute> OPACITY = create(
            "generic.opacity",
            new ClampedEntityAttribute("attribute.name.generic.opacity", 1.0, 0.0, 1.0)
                    .setTracked(true).setCategory(EntityAttribute.Category.NEUTRAL)
    );

    private static RegistryEntry<EntityAttribute> create(String name, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, AcornLib.id(name), attribute);
    }

    static void init() {}
}
