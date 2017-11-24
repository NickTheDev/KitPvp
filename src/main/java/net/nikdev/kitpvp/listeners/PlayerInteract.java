package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.lang.LangKeys;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static net.nikdev.kitpvp.lang.LangKeys.FIRST_LOCATION_SET;
import static net.nikdev.kitpvp.lang.LangKeys.SECOND_LOCATION_SET;

/**
 * Listener implementation for the {@link PlayerInteractEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerInteract implements Listener {

    /**
     * Listens for the specified event and handles wand input.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void wandInteract(PlayerInteractEvent event) {
        if(event.getItem() != null && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals(Chat.color("&e&lKitPvp Location Wand"))) {
            User user = User.get(event.getPlayer().getUniqueId()).get();

            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                user.getCache().set("first-location", event.getClickedBlock().getLocation());
                LangKeys.sendTo(user, FIRST_LOCATION_SET);

            } else if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                user.getCache().set("second-location", event.getClickedBlock().getLocation());
                LangKeys.sendTo(user, SECOND_LOCATION_SET);

                event.setCancelled(true);
            }

        }

    }

}
