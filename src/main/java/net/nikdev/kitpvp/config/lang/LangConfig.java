package net.nikdev.kitpvp.config.lang;

import net.nikdev.kitpvp.config.Configs;
import net.nikdev.kitpvp.config.StoreException;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Config that holds message (language) values used in KitPvp.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class LangConfig {

    private final FileConfiguration config;

    /**
     * Creates a new language config and loads the values from the Yaml flatfile.
     *
     * @throws StoreException Thrown if an error occurs loading the values.
     */
    public LangConfig() throws StoreException {
        config = Configs.load("lang", true);
    }

    /**
     * Gets the language value associated with the specified key.
     *
     * @param key Key of the value.
     * @return Language value associated with the key.
     */
    public String get(String key) {
        return config.getString(key, "");
    }

}
