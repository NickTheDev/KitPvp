package net.nikdev.kitpvp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A utility for storing object data in runtime.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Cache {

    private final Map<String, Object> data = new HashMap<>();

    /**
     * Gets if this cache contains a value associated with the specified key.
     *
     * @param key Key associated with a value.
     * @return If this cache contains the specified key.
     */
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    /**
     * Gets the value associated with the specified key if it is present. Otherwise, Optional.empty() will be returned.
     *
     * @param key Key associated with the value.
     * @param <T> Type of value.
     * @return Value associated with the key.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> get(String key) {
        if (contains(key)) {
            try {
                return Optional.of((T) data.get(key));

            } catch (ClassCastException ignored) {}

        }

        return Optional.empty();
    }

    /**
     * Puts the specified key and value into this cache if they are not null. If this cache already contains
     * a mapping with the key, the current value will be overwritten.
     *
     * @param key Key associated with the value.
     * @param value Value to be put into the cache.
     */
    public void set(String key, Object value) {
        if(key != null && value != null) {
            data.put(key, value);
        }

    }

    /**
     * Puts the specified keys and values into this cache if they not null. If this cache already contains
     * mappings with the keys, the current values will be overwritten.
     *
     * @param values Values to be put into the cache.
     */
    public void set(Map<String, Object> values) {
        if(values != null) {
            data.putAll(values);
        }

    }

    /**
     * Removes the specified key mapping from this cache if this cache contains the specified key.
     *
     * @param key Key mapping to be removed.
     */
    public void remove(String key) {
        data.remove(key);
    }

}
