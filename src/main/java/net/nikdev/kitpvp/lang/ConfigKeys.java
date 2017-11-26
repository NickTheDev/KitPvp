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
     * Database address.
     */
    DATABASE_ADDRESS("database.address"),

    /**
     * Database port.
     */
    DATABASE_PORT("database.port"),

    /**
     * Database name.
     */
    DATABASE_NAME("database.name"),

    /**
     * Database user.
     */
    DATABASE_USER("database.user"),

    /**
     * Database password.
     */
    DATABASE_PASSWORD("database.password"),

    /**
     * Whether or not rain is allowed on the server.
     */
    ALLOW_RAIN("world.allow-rain"),

    /**
     * Whether or not hunger is allowed on the server.
     */
    ALLOW_HUNGER("world.allow-hunger");

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

    /**
     * Gets the integer from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static int getInt(ConfigKeys key) {
        return KitPvp.get().getConfig().getInt(key.toString(), 0);
    }

    /**
     * Gets the boolean from {@link KitPvp}'s config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the config.
     */
    public static boolean getBool(ConfigKeys key) {
        return KitPvp.get().getConfig().getBoolean(key.toString(), false);
    }

}
