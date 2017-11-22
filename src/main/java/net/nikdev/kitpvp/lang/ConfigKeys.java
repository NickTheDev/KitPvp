package net.nikdev.kitpvp.lang;

import net.nikdev.kitpvp.KitPvp;

/**
 * Utility for accessing plugin config preferences.
 *
 * @author NickTheDev
 * @since 1.0
 */
public enum ConfigKeys {

    /**
     * Data Store user key.
     */
    USER_KEY("datastore.user"),

    /**
     * Data Store password key.
     */
    AUTH_KEY("datastore.password");

    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    ConfigKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * Gets the string from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static String get(ConfigKeys key) {
        return KitPvp.get().getConfig().getString(key.toString(), "");
    }

}
