package net.nikdev.kitpvp.user;

import com.google.common.collect.ImmutableList;
import net.nikdev.kitpvp.KitPvp;

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
     * Creates a new user object from the specified Bukkit counterpart. This should only be called asynchronously.
     *
     * @param id Id of the user.
     * @param name Name of the user.
     */
    public void load(UUID id, String name) {
        online.add(new User(id, name, KitPvp.get().getStore().load(id)));
    }

    /**
     * Saves the user's statistics synchronously and then removes the user from the online collection.
     *
     * @param user User to save.
     */
    public void save(User user) {
        KitPvp.get().getStore().update(user.getStats());

        online.remove(user);
    }

    /**
     * Saves all users synchronously, as this will only be called when the plugin is disabling.
     */
    public void saveAll() {
        new ArrayList<>(getOnline()).forEach(this::save);
    }

}
