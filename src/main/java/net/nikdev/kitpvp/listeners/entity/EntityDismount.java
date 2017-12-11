package net.nikdev.kitpvp.listeners.entity;

import net.nikdev.kitpvp.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

/**
 * Listener implementation for the {@link EntityDismountEvent}.
 *;
 * @author NickTheDev
 * @since 1.0
 */
public class EntityDismount implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void entityDismount(EntityDismountEvent event) {
        if(event.getDismounted() instanceof Player && User.get(event.getDismounted().getUniqueId()).get().getCache().contains("picked-up")) {
            event.getEntity().setPassenger(event.getDismounted());
        }

    }

}
