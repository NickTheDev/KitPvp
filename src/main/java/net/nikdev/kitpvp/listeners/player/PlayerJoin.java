package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.menu.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

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

        if(!Lang.get(Keys.PLAYER_JOIN).isEmpty()) {
            event.setJoinMessage(Lang.get(Keys.PLAYER_JOIN, Placeholder.of("name", user.getName())));
        }

        Optional<Location> spawn = KitPvp.get().getLocations().getSpawn();

        if(spawn.isPresent()) {
            user.toPlayer().teleport(spawn.get());

        } else {
            Lang.sendTo(user, Keys.SPAWN_NOT_SET);
        }

        user.clean();

        user.give(ItemBuilder.builder(Material.CHEST).name("&f&lKit Selector"));
        user.give(ItemBuilder.builder(Material.CHEST).name("&f&lKit Shop"), 4);
        user.give(ItemBuilder.builder(Material.CHEST).name("&f&lPrevious Kit"), 9);
    }

}
