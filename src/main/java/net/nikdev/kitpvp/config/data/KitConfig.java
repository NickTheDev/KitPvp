package net.nikdev.kitpvp.config.data;

import net.nikdev.kitpvp.config.Configs;
import net.nikdev.kitpvp.config.StoreException;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.kit.callbacks.Pvp;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Config that holds {@link Kit} declarations.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class KitConfig {

    private final FileConfiguration config;

    /**
     * Creates a new kit config and loads the values from the Yaml flatfile.
     *
     * @throws StoreException Thrown if an error occurs loading the values.
     */
    public KitConfig() throws StoreException {
        config = Configs.load("kits", true);

        load();
    }

    /**
     * Reads the kit declaration and returns the deserialized kits.
     */
    private void load() {
        config.getConfigurationSection("kits").getKeys(false).forEach(key -> {
            ConfigurationSection section = config.getConfigurationSection("kits." + key);

            KitCallback action;

            try {
                action = (KitCallback) Class.forName(section.getString("callback")).newInstance();

            } catch (ReflectiveOperationException e) {
                e.printStackTrace();

                action = new Pvp();
            }

            Kit.register(new Kit(section.getString("id"), section.getString("name"), section.getString("description"),
                    Material.matchMaterial(section.getString("icon")), section.contains("icon-data") ? (short) section.getInt("icon-data") : null,
                    section.getInt("cost"), action));
        });

    }

}
