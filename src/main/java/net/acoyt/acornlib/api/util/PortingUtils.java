package net.acoyt.acornlib.api.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.Util;
import org.joml.Vector3f;

import java.util.List;

import static net.minecraft.util.math.ColorHelper.Argb.*;
import static net.minecraft.util.math.ColorHelper.channelFromFloat;

public class PortingUtils {
    public static final Codec<Vector3f> VECTOR_3F = Codec.FLOAT
            .listOf()
            .comapFlatMap(
                    list -> Util.decodeFixedLengthList(list, 3).map(listX -> new Vector3f(listX.get(0), listX.get(1), listX.get(2))),
                    vec3f -> List.of(vec3f.x(), vec3f.y(), vec3f.z())
            );

    public static final Codec<Integer> RGB = Codec.withAlternative(Codec.INT, VECTOR_3F, vec3f -> fromFloats(1.0F, vec3f.x(), vec3f.y(), vec3f.z()));

    public static int fromFloats(float alpha, float red, float green, float blue) {
        return getArgb(channelFromFloat(alpha), channelFromFloat(red), channelFromFloat(green), channelFromFloat(blue));
    }

    public static Vector3f toVector(int rgb) {
        float f = getRed(rgb) / 255.0F;
        float g = getGreen(rgb) / 255.0F;
        float h = getBlue(rgb) / 255.0F;
        return new Vector3f(f, g, h);
    }
}
