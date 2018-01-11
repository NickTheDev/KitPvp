package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

/**
 * Listener implementation for the {@link PlayerFishEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerToggleSneak implements Listener {

    /**
     * Listens for the specified event for the vampire kit.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void vampireSneak(PlayerToggleSneakEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getKit().isPresent() && user.getCache().contains("vampire-victim")) {
            user.getCache().remove("vampire-victim");
        }

    }

}
