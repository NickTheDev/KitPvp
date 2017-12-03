package net.nikdev.kitpvp.config;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.kit.callbacks.Pvp;
import net.nikdev.kitpvp.util.StoreException;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Config that holds {@link Kit} declarations.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class KitConfig {

    private final FileConfiguration config;

    /**
     * Creates a new language config and loads the values from the Yaml flatfile.
     *
     * @throws StoreException Thrown if an error occurs loading the values.
     */
    public KitConfig() throws StoreException {
        try {
            Path path = new File(KitPvp.get().getDataFolder(), "kits.yml").toPath();

            if(Files.notExists(path)) {
                Files.copy(getClass().getResourceAsStream("/kits.yml"), path);
            }

            config = YamlConfiguration.loadConfiguration(path.toFile());

        } catch(IOException e) {
            throw new StoreException("An error occurred opening the kit storage.", e);
        }

    }

    /**
     * Reads the kit declaration and returns the deserialized kits.
     */
    public void readKits() {
        List<Kit> loaded = new ArrayList<>();

        config.getConfigurationSection("kits").getValues(false).keySet().forEach(key -> {
            ConfigurationSection section = config.getConfigurationSection("kits." + key);

                    KitCallback action;

            try {
                action = (KitCallback) Class.forName("net.nikdev.kitpvp.kit.callbacks." + section.getString("callback")).newInstance();

            } catch (ReflectiveOperationException e) {
                e.printStackTrace();

                action = new Pvp();
            }

            loaded.add(new Kit(section.getString("id"), section.getString("name"), section.getString("description"),
                    Material.matchMaterial(section.getString("icon")), section.contains("icon-data") ? (short) section.getInt("icon-data") : null,
                    section.getInt("cost"), action));
        });

        Kit.getKits().addAll(loaded);
    }

}
