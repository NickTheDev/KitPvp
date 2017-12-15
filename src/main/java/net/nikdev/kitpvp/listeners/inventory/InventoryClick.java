package net.nikdev.kitpvp.listeners.inventory;

import net.nikdev.kitpvp.menu.Menu;
import net.nikdev.kitpvp.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Listener implementation for the {@link InventoryClickEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class InventoryClick implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        User user = User.get(event.getWhoClicked().getUniqueId()).get();

        if(!user.getKit().isPresent()) {
            event.setCancelled(true);
        }

        if(user.getCache().contains("menu")) {
            event.setCancelled(true);

            if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                ((Menu) user.getCache().get("menu")).getCallback().ifPresent(callback -> callback.interact(user, event.getCurrentItem()));
            }

        }

    }

}