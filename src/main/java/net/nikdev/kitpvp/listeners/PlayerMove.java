package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.location.Cuboid;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

import static net.nikdev.kitpvp.lang.LangKeys.NO_KIT_SELECTED;

/**
 * Listener implementation for the {@link PlayerMoveEvent}.
 *;
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerMove implements Listener {

    /**
     * Listens for the specified event and handles spawn movements.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void spawnMove(PlayerMoveEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        Optional<Cuboid> region = KitPvp.get().getLocations().getRegion();

        if(!region.isPresent() || user.getKit().isPresent()) {
            return;
        }

        if(region.get().isInside(event.getFrom()) && !region.get().isInside(event.getTo())) {
            event.setCancelled(true);

            LangKeys.sendTo(user, NO_KIT_SELECTED);
        } else if(region.get().isInside(event.getTo()) && !region.get().isInside(event.getFrom())) {
            event.setCancelled(true);
        }

    }

    /**
     * Listens for the specified event and handles the shark kit's ability.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void sharkMove(PlayerMoveEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getKit().isPresent() && user.getKit().get().getId().equals("shark")) {
            if(event.getFrom().getBlock().getType().equals(Material.AIR) && event.getTo().getBlock().getType().equals(Material.WATER) ||
                    event.getTo().getBlock().getType().equals(Material.STATIONARY_WATER)) {
                user.toPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1));

                user.toPlayer().setAllowFlight(true);
                user.toPlayer().setFlying(true);
            } else if(event.getFrom().getBlock().getType().equals(Material.WATER) || event.getFrom().getBlock().getType().equals(Material.STATIONARY_WATER) &&
                    event.getTo().getBlock().getType().equals(Material.AIR)) {
                user.toPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);

                user.toPlayer().setAllowFlight(false);
                user.toPlayer().setFlying(false);
            }

        }

    }

}
