package net.nikdev.kitpvp;

import net.nikdev.kitpvp.command.CommandManager;
import net.nikdev.kitpvp.config.KitConfig;
import net.nikdev.kitpvp.config.LangConfig;
import net.nikdev.kitpvp.config.LocationConfig;
import net.nikdev.kitpvp.listeners.entity.EntityDamage;
import net.nikdev.kitpvp.listeners.entity.ProjectileHit;
import net.nikdev.kitpvp.listeners.player.*;
import net.nikdev.kitpvp.listeners.world.BlockBreak;
import net.nikdev.kitpvp.listeners.world.BlockPlace;
import net.nikdev.kitpvp.listeners.world.WeatherChange;
import net.nikdev.kitpvp.user.stats.DataStore;
import net.nikdev.kitpvp.user.UserManager;
import net.nikdev.kitpvp.util.StoreException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Main plugin implementation for KitPvp.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class KitPvp extends JavaPlugin {

    private static KitPvp kitpvp;
    private boolean failed;

    private DataStore store;
    private LocationConfig locations;
    private LangConfig lang;
    private KitConfig kits;

    private UserManager userManager;

    @Override
    public void onEnable() {
        kitpvp = this;

        saveDefaultConfig();

        try {
            locations = new LocationConfig();
            lang = new LangConfig();
            kits = new KitConfig();
            store = new DataStore();

        } catch (StoreException e) {
            getLogger().severe("An error occurred opening the stats store, disabling.");
            e.printStackTrace();

            Bukkit.getPluginManager().disablePlugin(this);
            failed = true;

            return;
        }

        userManager = new UserManager();

        getKits().readKits();
        registerListeners();
        getCommand("kitpvp").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        if(failed) {
            return;
        }

        getUserManager().saveAll();
        getLocations().save();
        getStore().close();
    }

    /**
     * Gets the loaded instance of KitPvp.
     *
     * @return Loaded KitPvp instance.
     */
    public static KitPvp get() {
        return kitpvp;
    }

    /**
     * Gets the stats store of KitPvp.
     *
     * @return KitPvp's store.
     */
    public DataStore getStore() {
        return store;
    }

    /**
     * Gets the location config of KitPvp.
     *
     * @return KitPvp's location config.
     */
    public LocationConfig getLocations() {
        return locations;
    }

    /**
     * Gets the language config of KitPvp.
     *
     * @return KitPvp's language config.
     */
    public LangConfig getLang() {
        return lang;
    }

    /**
     * Gets the kit config of KitPvp.
     *
     * @return KitPvp's kit config.
     */
    public KitConfig getKits() {
        return kits;
    }

    /**
     * Gets the user manager of KitPvp.
     *
     * @return KitPvp's user manager.
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Registers KitPvp's listeners.
     */
    private void registerListeners() {
        Arrays.asList(new AsyncPlayerPreLogin(), new PlayerQuit(), new PlayerInteract(), new PlayerJoin(), new BlockBreak(), new BlockPlace(),
                new FoodLevelChange(), new EntityDamage(), new WeatherChange(), new PlayerFish(), new PlayerMove(), new ProjectileHit(),
                new PlayerInteractEntity(), new InventoryClose()).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

}
