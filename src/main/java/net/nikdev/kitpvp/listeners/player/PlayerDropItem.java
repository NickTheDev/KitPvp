package net.nikdev.kitpvp.listeners.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listener implementation for the {@link PlayerDropItemEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerDropItem implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

}
