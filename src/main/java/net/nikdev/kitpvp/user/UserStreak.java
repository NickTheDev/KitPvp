package net.nikdev.kitpvp.user;

/**
 * Represents a {@link User} kill streak which awards them extra tokens for kills.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class UserStreak {

    private final String name;
    private final int killsNeeded, award;

    /**
     * Creates a new kill streak with the specified information.
     *
     * @param name Name of this streak.
     * @param killsNeeded Kills needed to obtain this streak.
     * @param award Award of this streak.
     */
    public UserStreak(String name, int killsNeeded, int award) {
        this.name = name;
        this.killsNeeded = killsNeeded;
        this.award = award;
    }

    /**
     * Gets the name of this streak.
     *
     * @return This streak's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the amount of kills needed for this streak to be obtained.
     *
     * @return The amount of kills needed for this streak.
     */
    public int getKillsNeeded() {
        return killsNeeded;
    }

    /**
     * Gets the award of this streak.
     *
     * @return This streak's award.
     */
    public int getAward() {
        return award;
    }

}
