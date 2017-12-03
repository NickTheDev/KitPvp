package net.nikdev.kitpvp.config;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.util.StoreException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        try {
            Path path = new File(KitPvp.get().getDataFolder(), "lang.yml").toPath();

            if(Files.notExists(path)) {
                Files.copy(getClass().getResourceAsStream("/lang.yml"), path);
            }

            config = YamlConfiguration.loadConfiguration(path.toFile());

        } catch(IOException e) {
            throw new StoreException("An error occurred opening the location storage.", e);
        }

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
