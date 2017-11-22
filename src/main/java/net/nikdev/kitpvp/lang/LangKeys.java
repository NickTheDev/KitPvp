package net.nikdev.kitpvp.lang;

import net.nikdev.kitpvp.KitPvp;

/**
 * Utility for accessing plugin language preferences.
 *
 * @author NickTheDev
 * @since 1.0
 */
public enum LangKeys {

    ;

    private final String key;

    /**
     * Creates a new config key with the specified key.
     *
     * @param key Key of the config key.
     */
    LangKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    /**
     * Gets the string from {@link KitPvp}'s language config which is mapped to the specified key.
     *
     * @param key Key associated with the value.
     * @return Value from the language config.
     */
    public static String get(LangKeys key) {
        return KitPvp.get().getLang().get(key.toString());
    }

}
