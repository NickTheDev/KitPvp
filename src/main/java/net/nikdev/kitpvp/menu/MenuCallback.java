package net.nikdev.kitpvp.menu;

import net.nikdev.kitpvp.user.User;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a callback that will be notified when a {@link User} interacts with a {@link Menu}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public interface MenuCallback {

    /**
     * Called when a user interacts with the menu this callback has been registered to.
     *
     * @param user User that interacted.
     * @param item Item that was interacted with.
     */
    void interact(User user, ItemStack item);

}
