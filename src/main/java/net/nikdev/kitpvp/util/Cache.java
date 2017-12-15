package net.nikdev.kitpvp.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

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
     * Gets the value associated with the specified key. If there is no value associated with the key an
     * exception will be thrown.
     *
     * @param key Key associated with the value.
     * @param <T> Type of value.
     * @throws NoSuchElementException Thrown if there is no value with the key.
     * @return Value associated with the key.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        if(!data.containsKey(key)) {
            throw new NoSuchElementException("No value associated with specified key: " + key);
        }

        try {
            return (T) data.get(key);

        } catch (ClassCastException e) {
            throw new NoSuchElementException("No value with associated type and key: " + key);
        }

    }

    /**
     * Gets the value associated with the specified key if it is present or the default value.
     *
     * @param key Key associated with the value.
     * @param <T> Type of value.
     * @return Value associated with the key.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, T other) {
        return (T) data.getOrDefault(key, other);
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
     * Removes the specified key mapping from this cache if this cache contains the specified key.
     *
     * @param key Key mapping to be removed.
     */
    public void remove(String key) {
        data.remove(key);
    }

}
