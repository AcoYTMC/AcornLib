package net.acoyt.acornlib.impl.util.interfaces;

import java.util.Optional;

/**
 * @author AcoYT
 */
public interface LangDiffering<T> {
    default Optional<String> getDifferedKey(T object) {
        return Optional.empty();
    }
}
