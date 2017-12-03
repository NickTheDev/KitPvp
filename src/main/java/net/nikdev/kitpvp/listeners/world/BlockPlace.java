package net.nikdev.kitpvp.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Listener implementation for the {@link BlockPlaceEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class BlockPlace implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void blockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().hasPermission("kitpvp.edit-terrain")) {
            event.setCancelled(true);
        }

    }

}
