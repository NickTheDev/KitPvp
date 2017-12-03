package net.nikdev.kitpvp.kit.callbacks;

import net.nikdev.kitpvp.kit.KitCallback;
import net.nikdev.kitpvp.user.User;
import org.bukkit.inventory.ItemStack;

/**
 * Action implementation for the Luigi kit.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class Luigi implements KitCallback {

    @Override
    public void give(User user) {
        fillSoup(user);
    }

    @Override
    public void interact(User user, ItemStack item, boolean right) {
        // TODO Ability doesn't make any sense so gotta figure that out.
    }

}
