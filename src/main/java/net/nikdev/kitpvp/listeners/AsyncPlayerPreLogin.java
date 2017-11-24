package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.KitPvp;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Listener implementation for the {@link AsyncPlayerPreLoginEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class AsyncPlayerPreLogin implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void asyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        KitPvp.get().getUserManager().load(event.getUniqueId(), event.getName());
    }

}
