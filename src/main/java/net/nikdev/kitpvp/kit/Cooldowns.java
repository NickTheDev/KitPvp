package net.nikdev.kitpvp.kit;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Bukkit;

/**
 * Utilities for simulating cooldowns for {@link Kit} abilities.
 *
 * @author NickTheDev
 * @since 1.0
 */
public final class Cooldowns {

    /**
     * Utility class, not to be instantiated.
     */
    private Cooldowns() {}

    /**
     * Checks if the user has a cooldown for the tag.
     *
     * @param user User to check.
     * @param tag Tag to check.
     * @return If the user has a cooldown.
     */
    public static boolean check(User user, String tag) {
        if(user.getCache().contains(tag)) {
            Lang.sendTo(user, Lang.COOLDOWN);

            return true;
        }

        return false;
    }

    /**
     * Starts a cooldown for the specified user and duration.
     *
     * @param user User to start a cooldown for.
     * @param tag Tag of the cooldown.
     * @param duration Duration fo the cooldown.
     */
    public static void start(User user, String tag, int duration) {
        user.getCache().set(tag, true);

        Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove(tag), duration);
    }

    /**
     * Starts a cooldown for the specified user and duration.
     *
     * @param user User to start a cooldown for.
     * @param tag Tag of the cooldown.
     * @param duration Duration fo the cooldown.
     * @param tasks Tasks to run when the cooldown expires.
     */
    public static void start(User user, String tag, int duration, Runnable tasks) {
        user.getCache().set(tag, true);

        Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> {
            user.getCache().remove(tag);

            tasks.run();
        }, duration);
    }

}
