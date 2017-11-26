package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import static net.nikdev.kitpvp.lang.LangKeys.*;

/**
 * Listener implementation for the {@link PlayerFishEvent}.
 *;
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
    public void blockBreak(PlayerFishEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getKit().isPresent() && user.getKit().get().getId().equals("fisherman") && event.getCaught() != null && event.getCaught() instanceof Player) {
            Location location = user.toPlayer().getLocation();

            event.getCaught().teleport(location.add(location.getDirection()));
            LangKeys.sendTo(event.getCaught(), FISHERMAN_HOOKED, Placeholder.of("name", user.getName()));
        }

    }

}
