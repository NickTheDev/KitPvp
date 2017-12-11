package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.packet.Title;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listener implementation for the {@link PlayerJoinEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerJoin implements Listener {

    private static final Title title = Title.builder().title(Lang.get(Lang.JOIN_TITLE)).fadeIn(2).fadeOut(2).stay(3).build();

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        event.setJoinMessage(Lang.get(Lang.PLAYER_JOIN, Placeholder.of("name", user.getName())));
        title.send(user);
        user.spawn();
    }

}
