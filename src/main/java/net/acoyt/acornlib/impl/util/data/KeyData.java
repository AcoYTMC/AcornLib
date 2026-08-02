/*package net.acoyt.acornlib.impl.util.data;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
 */

/**
 * @author AcoYT
 */
/*public record KeyData(String name, int keyCode, KeyMapping.Category category) {
    public static final Codec<KeyMapping.Category> CATEGORY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(KeyMapping.Category::id)
    ).apply(instance, KeyMapping.Category::new));

    public static final Codec<KeyData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(KeyData::name),
            Codec.INT.fieldOf("keyCodec").forGetter(KeyData::keyCode),
            CATEGORY_CODEC.fieldOf("category").forGetter(KeyData::category)
    ).apply(instance, KeyData::new));

    public KeyMapping asKeyMapping() {
        return new KeyMapping(name, InputConstants.Type.KEYSYM, keyCode, category);
    }
}
 */
