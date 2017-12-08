package net.nikdev.kitpvp.listeners.inventory;

import net.nikdev.kitpvp.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Listener implementation for the {@link InventoryCloseEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class InventoryClose implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getCache().contains("menu")) {
            user.getCache().remove("menu");
        }

    }

}
