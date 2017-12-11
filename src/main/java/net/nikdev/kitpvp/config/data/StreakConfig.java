package net.nikdev.kitpvp.config.data;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.Configs;
import net.nikdev.kitpvp.config.StoreException;
import net.nikdev.kitpvp.user.UserStreak;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Config that holds {@link UserStreak} declarations.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class StreakConfig {

    private final FileConfiguration config;

    /**
     * Creates a new streak config and loads the values from the Yaml flatfile.
     *
     * @throws StoreException Thrown if an error occurs loading the values.
     */
    public StreakConfig() throws StoreException {
        config = Configs.load("streaks", true);

        load();
    }

    /**
     * Reads the streak declaration and returns the deserialized streaks.
     */
    private void load() {
        config.getConfigurationSection("kill-streaks").getKeys(false).forEach(key -> {
            ConfigurationSection section = config.getConfigurationSection("kill-streaks." + key);

            KitPvp.get().getUserManager().registerStreak(new UserStreak(section.getString("name"), section.getInt("kills-needed"), section.getInt("award")));
        });

    }

}
