package net.nikdev.kitpvp;

import net.nikdev.kitpvp.lang.LangConfig;
import net.nikdev.kitpvp.location.LocationConfig;
import net.nikdev.kitpvp.stats.DataStore;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.user.UserManager;
import net.nikdev.kitpvp.util.StoreException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collection;

/**
 * Main plugin implementation for KitPvp.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class KitPvp extends JavaPlugin {

    private static KitPvp kitpvp;

    private DataStore store;
    private LocationConfig locations;
    private LangConfig lang;

    private UserManager userManager;

    @Override
    public void onEnable() {
        kitpvp = this;

        saveDefaultConfig();

        try {
            locations = new LocationConfig();
            lang = new LangConfig();
            store = new DataStore();

        } catch (StoreException e) {
            getLogger().severe("An error occurred opening the stats store, disabling.");
            e.printStackTrace();

            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        userManager = new UserManager();
    }

    @Override
    public void onDisable() {
        if(getLocations() != null && getStore() != null) {
            getLocations().save();
            getStore().close();
        }

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
     * Shorthand for UserManager#getOnline.
     *
     * @return All online users.
     */
    public Collection<User> getOnline() {
        return getUserManager().getOnline();
    }

}
