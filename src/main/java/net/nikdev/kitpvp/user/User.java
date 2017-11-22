package net.nikdev.kitpvp.user;

import net.nikdev.kitpvp.stats.Statistics;
import net.nikdev.kitpvp.util.Cache;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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
     * Gets the Bukkit representation of this user.
     *
     * @return Bukkit's player for this user.
     */
    public Player toPlayer() {
        return Bukkit.getPlayer(getId());
    }

}
