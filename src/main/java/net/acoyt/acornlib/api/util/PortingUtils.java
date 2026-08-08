package net.acoyt.acornlib.api.util;

//? if < 1.21.5 {
/*import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.util.FastColor;
import org.joml.Vector3f;

import java.util.List;
import java.util.Optional;
*///? }

/**
 * @author AcoYT
 */
//? if < 1.21.5 {
/*public class PortingUtils {
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

    public static Vector3f toVector(int rgb) {
        float f = FastColor.ARGB32.red(rgb) / 255.0F;
        float g = FastColor.ARGB32.green(rgb) / 255.0F;
        float h = FastColor.ARGB32.blue(rgb) / 255.0F;
        return new Vector3f(f, g, h);
    }

    public static <T> Optional<T> read(String name, Codec<T> codec, CompoundTag tag, HolderLookup.Provider provider) {
        if (!tag.contains(name)) {
            return Optional.empty();
        } else {
            return switch (codec.parse(provider.createSerializationContext(NbtOps.INSTANCE), tag.get(name))) {
                case DataResult.Success<T> success -> Optional.of(success.value());
                case DataResult.Error<T> error -> error.partialValue();
            };
        }
    }

    public static <T> void store(String name, Codec<T> codec, T value, CompoundTag tag, HolderLookup.Provider provider) {
        switch (codec.encodeStart(provider.createSerializationContext(NbtOps.INSTANCE), value)) {
            case DataResult.Success<Tag> success:
                tag.put(name, success.value());
                break;
            case DataResult.Error<Tag> error:
                error.partialValue().ifPresent(partial -> tag.put(name, partial));
                break;
            default:
                throw new MatchException(null, null);
        }
    }
}
*///? }