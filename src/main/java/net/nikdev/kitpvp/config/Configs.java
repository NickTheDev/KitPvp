package net.nikdev.kitpvp.config;

import net.nikdev.kitpvp.KitPvp;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Utilities used by all configurations to load and save the flatfiles.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Configs {

    /**
     * Utility class, not to be instantiated.
     */
    private Configs() {}

    /**
     * Attempts to load the configuration from the specified flatfile.
     *
     * @param name Name of the file.
     * @param copy Whether or a default config should be copied.
     * @return Loaded config.
     * @throws StoreException Thrown if an error occurs loading the file.
     */
    public static FileConfiguration load(String name, boolean copy) throws StoreException {
        try {
            Path path = new File(KitPvp.get().getDataFolder(), name + ".yml").toPath();

            if(Files.notExists(path) && copy) {
                Files.copy(Configs.class.getResourceAsStream("/" + name + ".yml"), path);
            }

            return YamlConfiguration.loadConfiguration(path.toFile());

        } catch(IOException e) {
            throw new StoreException("An error occurred opening " + name + ".yml", e);
        }

    }

    /**
     * Checks if the license has been exported, and if not will copy it.
     */
    public static void checkLicense() {
        try {
            Path license = new File(KitPvp.get().getDataFolder(), "LICENSE.txt").toPath();

            if(Files.notExists(license)) {
                Files.copy(Configs.class.getResourceAsStream("/LICENSE.txt"), license);
            }

        } catch(IOException e) {
            KitPvp.get().getLogger().severe("An error occurred copying the license.");

            e.printStackTrace();
        }

    }

    /**
     * Attempts toe save the configuration to the specified flatfiles.
     *
     * @param config Config to save.
     * @param name Name of the file to save to.
     */
    public static void save(FileConfiguration config, String name) {
        try {
            config.save(new File(KitPvp.get().getDataFolder(), name + ".yml"));

        } catch (IOException e) {
            KitPvp.get().getLogger().severe("An error occurred saving the location store.");

            e.printStackTrace();
        }

    }

}
