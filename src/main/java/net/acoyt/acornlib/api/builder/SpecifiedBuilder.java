package net.acoyt.acornlib.api.builder;

import net.acoyt.acornlib.impl.util.interfaces.IdContained;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author AcoYT
 */
public abstract class SpecifiedBuilder<T, O> implements IdContained {
    private final String modId;
    public final Map<T, O> specified = new HashMap<>();

    public SpecifiedBuilder(String modId) {
        this.modId = modId;
    }

    public String getModId() {
        return this.modId;
    }

    public T register(String name, O obj) {
        T object = this.applyFunction().apply(this.id(name));
        this.specified.put(object, obj);
        return object;
    }

    public abstract Function<ResourceLocation, T> applyFunction();
    public abstract BiConsumer<T, O> endFunction();

    public void build() {
        this.specified.forEach(this.endFunction());
    }
}
