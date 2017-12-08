package net.nikdev.kitpvp.user;

import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.util.packet.Title;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a {@link User} kill streak which awards them extra tokens for kills.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Streak {

    private static final List<Streak> streaks = new ArrayList<>();

    private final String name;
    private final int killsNeeded, award;

    /**
     * Creates a new kill streak with the specified information.
     *
     * @param name Name of this streak.
     * @param killsNeeded Kills needed to obtain this streak.
     * @param award Award of this streak.
     */
    public Streak(String name, int killsNeeded, int award) {
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

    /**
     * Applies all applicable (lol) streaks to the user according to his current kill streak.
     *
     * @param user User to apply streaks to.
     */
    public static void apply(User user) {
        user.getCache().set("kills", user.getCache().get("kills", 0) + 1);

        int kills = user.getCache().get("kills");
        int tokens = Config.getInt(Config.DEFAULT_AWARD);

        for(Streak streak : streaks) {
            if(kills >= streak.getKillsNeeded()) {
                tokens += streak.getAward();

                Lang.sendTo(user, Keys.PLAYER_STREAK, Placeholder.of("name", streak.getName()), Placeholder.of("amount", streak.getAward()));
            }

        }

        user.getStats().addTokens(tokens);
        Title.builder().title(Lang.get(Keys.DEATH_TOKEN_TITLE, Placeholder.of("amount", tokens))).fadeIn(1).fadeOut(1).stay(2).build().send(user);
    }

    /**
     * Registers the specified streak if it is not null and it has not already been registered.
     *
     * @param streak Streak to register.
     */
    public static void register(Streak streak) {
        if(streak != null && !streaks.contains(streak)) {
            streaks.add(streak);
        }

    }

}
