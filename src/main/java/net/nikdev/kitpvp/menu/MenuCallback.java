package net.nikdev.kitpvp.menu;

import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a callback that will be notified when a {@link User} interacts with a {@link Menu}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public interface MenuCallback {

    /**
     * Gets the if the item is the exit item and the callback should exit the menu.
     *
     * @param item Item to check.
     * @return If the callback should exit.
     */
    default boolean shouldExit(ItemStack item) {
        return item.getItemMeta().getDisplayName().equals(Chat.color(Config.get(Config.EXIT_ITEM_NAME)));
    }

    /**
     * Called when a user interacts with the menu this callback has been registered to.
     *
     * @param user User that interacted.
     * @param item Item that was interacted with.
     */
    void interact(User user, ItemStack item);

}
