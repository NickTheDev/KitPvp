package net.nikdev.kitpvp.user;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.stats.Statistics;
import net.nikdev.kitpvp.util.Cache;
import net.nikdev.kitpvp.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a player.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class User {

    private final UUID id;
    private final String name;

    private final Statistics stats;
    private final Cache cache = new Cache();

    /**
     * Creates a new user with the specified information.
     *
     * @param id Id of this user.
     * @param name Name of this user.
     * @param stats Stats of this user.
     */
    User(UUID id, String name, Statistics stats) {
        this.id = id;
        this.name = name;
        this.stats = stats;
    }

    /**
     * Gets the unique id of this player.
     *
     * @return This player's id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the name of this player.
     *
     * @return This player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the statistics of this player.
     *
     * @return This player's stats.
     */
    public Statistics getStats() {
        return stats;
    }

    /**
     * Gets the cache of this player.
     *
     * @return This player's cache.
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * Shorthand for getting this user's current kit.
     *
     * @return This user's kit.
     */
    public Optional<Kit> getKit() {
        return getCache().get("kit");
    }

    /**
     * Gets the Bukkit representation of this user.
     *
     * @return Bukkit's player for this user.
     */
    public Player toPlayer() {
        return Bukkit.getPlayer(getId());
    }

    /**
     * Gives this user the specified item in the next available slot.
     *
     * @param item Item to give.
     */
    public void give(ItemBuilder item) {
        toPlayer().getInventory().addItem(item.build());
    }

    /**
     * Gives this user the specified item in the specified inventory slot.
     *
     * @param item Item to give.
     * @param slot Slot of the item.
     */
    public void give(ItemBuilder item, int slot) {
        toPlayer().getInventory().setItem(slot, item.build());
    }

    /**
     * Sets the armor of this user to the specified optional items.
     *
     * @param armor Armor of this user.
     */
    public void setArmor(ItemBuilder... armor) {
        if(armor.length > 0 && armor.length < 5) {
            ItemStack[] items = new ItemStack[4];

            for(int i = 0; i < armor.length; i++) {
                items[i] = armor[i].build();
            }

            toPlayer().getInventory().setArmorContents(items);
        }

    }

    /**
     * Utility for "cleaning" this user and resetting all of their data and effects.
     */
    public void clean() {
        toPlayer().getInventory().clear();
        toPlayer().setHealth(20);
        toPlayer().setFoodLevel(20);
        toPlayer().setLevel(0);
        toPlayer().setExp(0F);
        toPlayer().setGameMode(GameMode.SURVIVAL);
        toPlayer().getActivePotionEffects().forEach(potion -> toPlayer().removePotionEffect(potion.getType()));
        toPlayer().setAllowFlight(false);
        toPlayer().setFlying(false);
    }

    /**
     * Shorthand for UserManager#get().
     *
     * @param name Name of the user.
     * @return User with the specified name or Optional.empty().
     */
    public static Optional<User> get(String name) {
        return KitPvp.get().getUserManager().get(name);
    }

    /**
     * Shorthand for UserManager#get().
     *
     * @param id Unique id of the user.
     * @return User with the specified id or Optional.empty().
     */
    public static Optional<User> get(UUID id) {
        return KitPvp.get().getUserManager().get(id);
    }

}
