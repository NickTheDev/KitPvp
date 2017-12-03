package net.nikdev.kitpvp.kit;

import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import net.nikdev.kitpvp.menu.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Callback for various kit related callbacks.
 *
 * @author NickTheDev
 * @since 1.0
 */
public interface KitCallback {

    /**
     * Checks that the item's name matches the specified name.
     *
     * @param item Item to check.
     * @param name Name to check against.
     * @return If both names match.
     */
    default boolean checkName(ItemStack item, String name) {
        return item.hasItemMeta() && item.getItemMeta().hasDisplayName() && Chat.strip(item.getItemMeta().getDisplayName()).equals(name);
    }

    /**
     * Fills the specified user's inventory to the brim with soups.
     *
     * @param user User to fill.
     */
    default void fillSoup(User user) {
        for(int i = 0; i < user.toPlayer().getInventory().getSize(); i++) {
            if(user.toPlayer().getInventory().getItem(i) == null) {
                user.give(ItemBuilder.builder(Material.MUSHROOM_SOUP), i);
            }

        }

    }

    /**
     * Called when the user selects this kit. The implementation should give the user this kit's initial items and effects.
     *
     * @param user User to give the kit to.
     */
    void give(User user);

    /**
     * Called when a user interacts with an item in the kit which may be a trigger for an ability. Not required to be
     * implemented as certain kits do not contain abilities.
     *
     * @param user User who interacted with the item.
     * @param item Item interacted with.
     * @param right If the interaction was a right click.
     */
    default void interact(User user, ItemStack item, boolean right) {}

}
