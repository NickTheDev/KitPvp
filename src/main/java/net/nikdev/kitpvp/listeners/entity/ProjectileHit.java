package net.nikdev.kitpvp.listeners.entity;

import net.nikdev.kitpvp.user.User;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Listener implementation for the {@link ProjectileHitEvent}.
 *;
 * @author NickTheDev
 * @since 1.0
 */
public class ProjectileHit implements Listener {

    /**
     * Listens for the specified event and handles exploding arrows for a kit.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void arrowHit(ProjectileHitEvent event) {
        if(event.getEntity() instanceof Arrow && !event.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.AIR) && event.getEntity().getPassenger() != null) {
            event.getEntity().eject();
        }

        if(event.getEntity() instanceof Arrow && event.getEntity().getShooter() instanceof Player) {
            User user = User.get(((Player) event.getEntity().getShooter()).getUniqueId()).get();

            if(user.getKit().isPresent() && user.getKit().get().getId().equals("crossbow")) {
                Location location = event.getEntity().getLocation();

                location.getWorld().createExplosion(location, 0, false);
            }

        }

    }

}
