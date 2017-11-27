package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static net.nikdev.kitpvp.lang.LangKeys.*;

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
        KitPvp.get().getUserManager().save(user);

        if(!LangKeys.get(PLAYER_QUIT).isEmpty()) {
            event.setQuitMessage(LangKeys.get(PLAYER_QUIT, Placeholder.of("name", user.getName())));
        }

    }

}
