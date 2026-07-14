package net.acoyt.acornlib.api.util;

//? if < 1.21.5 {
import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.util.FastColor;
import org.joml.Vector3f;

import java.util.List;
//? }

/**
 * @author AcoYT
 */
//? if < 1.21.5 {
public class PortingUtils {
    public static final Codec<Vector3f> VECTOR_3F = Codec.FLOAT
            .listOf()
            .comapFlatMap(
                    list -> Util.fixedSize(list, 3).map(listX -> new Vector3f(listX.get(0), listX.get(1), listX.get(2))),
                    vec3f -> List.of(vec3f.x(), vec3f.y(), vec3f.z())
            );

    public static final Codec<Integer> RGB = Codec.withAlternative(Codec.INT, VECTOR_3F, vec3f -> fromFloats(1.0F, vec3f.x(), vec3f.y(), vec3f.z()));

    public static int fromFloats(float alpha, float red, float green, float blue) {
        return FastColor.ARGB32.color(
                FastColor.as8BitChannel(alpha),
                FastColor.as8BitChannel(red),
                FastColor.as8BitChannel(green),
                FastColor.as8BitChannel(blue)
        );
    }
}
//? }