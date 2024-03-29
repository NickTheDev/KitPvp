package net.nikdev.kitpvp.user.stats;

import net.nikdev.kitpvp.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents the statistics of a {@link User}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Statistics {

    private final UUID id;
    private int tokens;
    private final List<String> kits = new ArrayList<>();

    /**
     * Creates a new statistic store with the specified information.
     *
     * @param id Id of this statistic store.
     * @param tokens Tokens of this statistic store.
     * @param kits Kits of this statistic store.
     */
    Statistics(UUID id, int tokens, List<String> kits) {
        this.id = id;
        this.tokens = tokens;

        // Add kits instead of setting the field to the parameter to make sure we are getting a copy of the default kits.
        this.kits.addAll(kits);
    }

    /**
     * Gets the unique id statistic of this store.
     *
     * @return Unique id statistic.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the token statistic from this store.
     *
     * @return Token statistic.
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Gets the kits statistic from this store.
     *
     * @return Kits statistic.
     */
    public List<String> getKits() {
        return kits;
    }

    @Override
    public String toString() {
        return "`tokens` = " + getTokens() + ", `kits` = '" + String.join(",", getKits()) + "'";
    }

    /**
     * Adds the specified amount of tokens to the token statistic.
     *
     * @param amount Amount of tokens to add.
     */
    public void addTokens(int amount) {
        tokens += amount;
    }

    /**
     * Removes the specified amount of tokens from the token statistic.
     *
     * @param amount Amount of tokens to remove.
     */
    public void removeTokens(int amount) {
        tokens -= amount;
    }

    /**
     * Sets the token statistic to the specified amount.
     *
     * @param amount Amount of tokens.
     */
    public void setTokens(int amount) {
        tokens = amount;
    }

}