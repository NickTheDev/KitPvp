package net.nikdev.kitpvp.kit;

import net.nikdev.kitpvp.user.User;
import org.bukkit.inventory.ItemStack;

/**
 * Represents {@link Kit} actions like receiving and interacting.
 *
 * @author NickTheDev
 * @since 1.0
 */
public interface KitAction {

    /**
     * Gives the specified user this kit's initial items and effects.
     *
     * @param user User to give the kit to.
     */
    void give(User user);

    /**
     * Called when a user interacts with an item in the kit which may be a trigger for an ability.
     *
     * @param user User who interacted with the item.
     * @param item Item interacted with.
     */
    void interact(User user, ItemStack item);

}
