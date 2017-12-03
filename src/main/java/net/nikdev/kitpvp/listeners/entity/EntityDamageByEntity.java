package net.nikdev.kitpvp.listeners.entity;

import net.nikdev.kitpvp.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Listener implementation for the {@link EntityDamageByEntityEvent}.
 *;
 * @author NickTheDev
 * @since 1.0
 */
public class EntityDamageByEntity implements Listener {

    /**
     * Listens for the specified event and handles making snowballs do damage.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void snowballDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            User user = User.get(event.getDamager().getUniqueId()).get();

            if(user.getKit().isPresent() && user.getKit().get().getId().equals("snowman")) {
                ((Player) event.getEntity()).damage(1.5);
            }

        }

    }

}
