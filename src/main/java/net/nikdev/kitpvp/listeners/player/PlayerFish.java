package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

/**
 * Listener implementation for the {@link PlayerFishEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerFish implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerFish(PlayerFishEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getKit().isPresent() && user.getKit().get().getId().equals("fisherman") && event.getCaught() != null && event.getCaught() instanceof Player) {
            if(user.getCache().contains("fisherman-fish-cooldown")) {
                Lang.sendTo(user, Lang.COOLDOWN);

                return;
            }

            Location location = user.toPlayer().getLocation();

            event.getCaught().teleport(location.add(location.getDirection()));
            Lang.sendTo(event.getCaught(), Lang.FISHERMAN_HOOKED, Placeholder.of("name", user.getName()));

            user.getCache().set("fisherman-fish-cooldown", true);
            Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> user.getCache().remove("fisherman-fish-cooldown"), 120);
        }

    }

}
