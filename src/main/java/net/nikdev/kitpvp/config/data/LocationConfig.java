package net.nikdev.kitpvp.config.data;

import net.nikdev.kitpvp.config.Configs;
import net.nikdev.kitpvp.config.StoreException;
import net.nikdev.kitpvp.util.Cuboid;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

import java.util.Optional;

/**
 * Config that holds location values such as the spawn and spawn region.
 *
 * @author NickTheDev
 */
public final class LocationConfig {

    private final FileConfiguration config;

    private Cuboid region;
    private Location spawn;

    /**
     * Creates or loads the location config store and all values contained in it.
     *
     * @throws StoreException Thrown if an error occurs opening the store.
     */
    public LocationConfig() throws StoreException {
        ConfigurationSerialization.registerClass(Cuboid.class);

        config = Configs.load("locations", false);
        populate();
    }

    /**
     * Gets the region stored in this config.
     *
     * @return This config's region.
     */
    public Optional<Cuboid> getRegion() {
        return Optional.ofNullable(region);
    }

    /**
     * Gets the spawn stored in this config.
     *
     * @return This config's spawn.
     */
    public Optional<Location> getSpawn() {
        return Optional.ofNullable(spawn);
    }

    /**
     * Sets the region of this config to the specified cuboid.
     *
     * @param region Region of this config.
     */
    public void setRegion(Cuboid region) {
        this.region = region;
    }

    /**
     * Sets the spawn of this config to the specified location.
     *
     * @param spawn Spawn of this config.
     */
    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    /**
     * Populates this config with values loaded from the Yaml flatfile.
     */
    private void populate() {
        if(config.contains("region")) {
            setRegion((Cuboid) config.get("region"));
        }

        if(config.contains("spawn")) {
            setSpawn((Location) config.get("spawn"));
        }

    }

    /**
     * Saves this location config to the Yaml flatfile.
     */
    public void save() {
        getSpawn().ifPresent((spawn) -> config.set("spawn", spawn));
        getRegion().ifPresent((region) -> config.set("region", region));

        Configs.save(config, "locations");
    }

}
