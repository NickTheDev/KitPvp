package net.nikdev.kitpvp.listeners.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Listener implementation for the {@link BlockBreakEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class BlockBreak implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void blockBreak(BlockBreakEvent event) {
        if(!event.getPlayer().hasPermission("kitpvp.edit-terrain")) {
            event.setCancelled(true);
        }

    }

}
