package net.nikdev.kitpvp.listeners.entity;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Cuboid;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Optional;

/**
 * Listener implementation for the {@link EntityDamageEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class EntityDamage implements Listener {

    /**
     * Listens for the specified event for handling spawn damage.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void spawnDamage(EntityDamageEvent event) {
        Optional<Cuboid> region = KitPvp.get().getLocations().getRegion();

        if(event.getEntity() instanceof Player && region.isPresent() && region.get().isInside(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }

    }

    /**
     * Listens for the specified event for the Mario and Luigi kits.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void jumpDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            User user = User.get(event.getEntity().getUniqueId()).get();

            if(user.getKit().isPresent() && user.getCache().contains("no-fall")) {
                event.setCancelled(true);

                user.getCache().remove("no-fall");
            }

        }

    }

}
