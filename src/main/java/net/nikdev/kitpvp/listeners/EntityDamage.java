package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.location.Cuboid;
import org.bukkit.entity.EntityType;
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
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void entityDamage(EntityDamageEvent event) {
        Optional<Cuboid> region = KitPvp.get().getLocations().getRegion();

        if(event.getEntityType().equals(EntityType.PLAYER) && region.isPresent() && region.get().inside(event.getEntity().getLocation())) {
            event.setCancelled(true);
        }

    }

}
