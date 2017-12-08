package net.nikdev.kitpvp.user;

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
        return online;
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
     * Creates a new user object from the specified Bukkit counterpart. This will only be called by an asynchronous event
     * and therefore does not need to handle running async tasks.
     *
     * @param id Id of the user.
     * @param name Name of the user.
     */
    public void load(UUID id, String name) {
        online.add(new User(id, name, KitPvp.get().getStore().load(id)));
    }

    /**
     * Saves the user's statistics asynchronously and removes the user from the online cache.
     * See Data store for an in depth explanation of the asynchronous parameter.
     *
     * @param user User to save.
     * @param async Whether or not to save the user's statistics asynchronously.
     */
    public void save(User user, boolean async) {
        online.remove(user);

        KitPvp.get().getStore().update(user.getStats(), async);
    }

    /**
     * Saves all users asynchronously after making a copy of the users to prevent a concurrent modification error.
     */
    public void saveAll() {
        new ArrayList<>(getOnline()).forEach(user -> save(user, false));
    }

}
