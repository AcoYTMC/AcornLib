package net.acoyt.acornlib.impl.util;

/**
 * @author AcoYT
 */
public interface QuadConsumer<K, V, S, T> {
    void accept(K k, V v, S s, T t);
}
