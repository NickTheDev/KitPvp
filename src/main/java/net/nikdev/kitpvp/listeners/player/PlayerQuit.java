package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener implementation for the {@link PlayerQuitEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerQuit implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerQuit(PlayerQuitEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();
        KitPvp.get().getUserManager().save(user, true);

        event.setQuitMessage(Lang.get(Lang.PLAYER_QUIT, Placeholder.of("name", user.getName())));
    }

}
