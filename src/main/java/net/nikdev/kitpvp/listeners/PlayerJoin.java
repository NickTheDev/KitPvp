package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

import static net.nikdev.kitpvp.lang.LangKeys.*;

/**
 * Listener implementation for the {@link PlayerJoinEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerJoin implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(!LangKeys.get(PLAYER_JOIN).isEmpty()) {
            event.setJoinMessage(LangKeys.get(PLAYER_JOIN, Placeholder.of("name", user.getName())));
        }

        Optional<Location> spawn = KitPvp.get().getLocations().getSpawn();

        if(spawn.isPresent()) {
            user.toPlayer().teleport(spawn.get());

        } else {
            LangKeys.sendTo(user, SPAWN_NOT_SET);
        }

        user.clean();
    }

}
