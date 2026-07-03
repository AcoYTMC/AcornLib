package net.acoyt.acornlib.impl.index;

import net.acoyt.acornlib.api.registrants.AttributeRegistrant;
import net.acoyt.acornlib.impl.AcornLib;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

/**
 * @author AcoYT
 */
public interface AcornAttributes {
    AttributeRegistrant ATTRIBUTES = new AttributeRegistrant(AcornLib.MOD_ID);

    Holder<Attribute> OPACITY = ATTRIBUTES.registerRef(
            "player.opacity",
            new RangedAttribute("attribute.name.player.opacity", 1.0, 0.0, 1.0)
                    .setSyncable(true).setSentiment(Attribute.Sentiment.NEUTRAL)
    );

    static void init() {}
}
