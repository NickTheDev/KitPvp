package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.config.lang.Keys;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Listener implementation for the {@link PlayerInteractEntityEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerInteractEntity implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerInteractEntity(PlayerInteractEntityEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(event.getRightClicked() instanceof Player && user.getKit().isPresent() && user.getKit().get().getId().equals("donkey-kong")) {
            ItemStack item = event.getPlayer().getItemInHand();

            if(item != null && item.getType().equals(Material.INK_SACK)) {
                Lang.sendTo(user, Keys.DONKEY_KONG_PICKUP, Placeholder.of("name", event.getRightClicked().getName()));

                user.toPlayer().setPassenger(event.getRightClicked());
            }

        }

    }

}
