package net.nikdev.kitpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Listener implementation for the {@link PlayerPickupItemEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerPickupItem implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

}
