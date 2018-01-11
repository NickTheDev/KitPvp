package net.nikdev.kitpvp;

import net.nikdev.kitpvp.command.CommandManager;
import net.nikdev.kitpvp.config.*;
import net.nikdev.kitpvp.config.data.KitConfig;
import net.nikdev.kitpvp.config.data.LocationConfig;
import net.nikdev.kitpvp.config.data.StreakConfig;
import net.nikdev.kitpvp.config.lang.LangConfig;
import net.nikdev.kitpvp.kit.KitManager;
import net.nikdev.kitpvp.listeners.entity.EntityDamage;
import net.nikdev.kitpvp.listeners.entity.EntityDismount;
import net.nikdev.kitpvp.listeners.entity.ProjectileHit;
import net.nikdev.kitpvp.listeners.inventory.InventoryClick;
import net.nikdev.kitpvp.listeners.inventory.InventoryClose;
import net.nikdev.kitpvp.listeners.player.*;
import net.nikdev.kitpvp.listeners.world.BlockBreak;
import net.nikdev.kitpvp.listeners.world.BlockPlace;
import net.nikdev.kitpvp.listeners.world.WeatherChange;
import net.nikdev.kitpvp.user.stats.DataStore;
import net.nikdev.kitpvp.user.UserManager;
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

    private UserManager userManager;
    private KitManager kitManager;

    @Override
    public void onEnable() {
        kitpvp = this;

        saveDefaultConfig();
        Configs.checkLicense();

        userManager = new UserManager();
        kitManager = new KitManager();

        try {
            new KitConfig();
            new StreakConfig();

            locations = new LocationConfig();
            lang = new LangConfig();
            store = new DataStore();

        } catch (StoreException e) {
            getLogger().severe(e.getMessage());
            e.printStackTrace();

            failed = true;
            Bukkit.getPluginManager().disablePlugin(this);

            return;
        }

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
     * Gets the user manager of KitPvp.
     *
     * @return KitPvp's user manager.
     */
    public UserManager getUserManager() {
        return userManager;
    }

    /**
     * Gets the kit manager of KitPvp.
     *
     * @return KitPvp's kit manager.
     */
    public KitManager getKitManager() {
        return kitManager;
    }

    /**
     * Registers KitPvp's listeners.
     */
    private void registerListeners() {
        Arrays.asList(new AsyncPlayerPreLogin(), new PlayerQuit(), new PlayerInteract(), new PlayerJoin(), new BlockBreak(), new BlockPlace(),
                new FoodLevelChange(), new EntityDamage(), new WeatherChange(), new PlayerFish(), new PlayerMove(), new ProjectileHit(),
                new PlayerInteractEntity(), new InventoryClose(), new InventoryClick(), new PlayerDeath(), new EntityDismount(), new PlayerTeleport(),
                new PlayerPickupItem(), new PlayerDropItem(), new PlayerToggleSneak())
                .forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

}
