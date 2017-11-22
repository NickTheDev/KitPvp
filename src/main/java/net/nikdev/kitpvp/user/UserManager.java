package net.nikdev.kitpvp.user;

import com.google.common.collect.ImmutableList;
import net.nikdev.kitpvp.KitPvp;
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Manages {@link User} and handles functions like saving and loading them.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class UserManager {

    private final List<User> online = new ArrayList<>();

    /**
     * Gets a copy of all online users.
     *
     * @return Online users.
     */
    public Collection<User> getOnline() {
        return ImmutableList.copyOf(online);
    }

    /**
     * Gets the user associated with the specified name. If there is no user associated with it,
     * Optional.empty() will be returned.
     *
     * @param name Name of the user.
     * @return User with the specified name or Optional.empty().
     */
    public Optional<User> get(String name) {
        return getOnline().stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    /**
     * Gets the user associated with the specified id. If there is no user associated with it,
     * Optional.empty() will be returned.
     *
     * @param id Unique id of the user.
     * @return User with the specified id or Optional.empty().
     */
    public Optional<User> get(UUID id) {
        return getOnline().stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    /**
     * Creates a new user object from the specified Bukkit counterpart.
     *
     * @param player Bukkit version of the user to load.
     * @return Loaded user.
     */
    public User create(Player player) {User user = new User(player.getUniqueId(), player.getName(), KitPvp.get().getStore().load(player.getUniqueId()));
        online.add(user);

        return user;
    }

    /**
     * Saves the user's statistics and then removes the user from the online collection.
     *
     * @param user User to save.
     */
    public void save(User user) {
        KitPvp.get().getStore().update(user.getStats());

        online.remove(user);
    }

}
