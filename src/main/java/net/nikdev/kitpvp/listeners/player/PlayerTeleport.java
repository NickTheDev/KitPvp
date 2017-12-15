package net.nikdev.kitpvp.listeners.player;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Listener implementation for the {@link PlayerTeleportEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerTeleport implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerTeleport(PlayerTeleportEvent event) {
        if(event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            for(Entity entity : event.getTo().getWorld().getEntities()) {
                if(entity instanceof Player && event.getTo().distance(entity.getLocation()) < 2) {
                    event.setCancelled(true);

                    Location previous = entity.getLocation();

                    entity.teleport(event.getPlayer().getLocation());
                    event.getPlayer().teleport(previous);
                }

            }

        }

    }

}
