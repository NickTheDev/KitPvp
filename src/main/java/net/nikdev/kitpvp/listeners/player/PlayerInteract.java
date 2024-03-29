package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.kit.Kit;
import net.nikdev.kitpvp.menu.kit.KitSelector;
import net.nikdev.kitpvp.menu.kit.KitShop;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.Chat;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
        if(event.getItem() != null && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && Chat.plain(event.getItem().getItemMeta().getDisplayName()).equals("KitPvp Location Wand")) {
            User user = User.get(event.getPlayer().getUniqueId()).get();

            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                user.getCache().set("first-location", event.getClickedBlock().getLocation());
                Lang.sendTo(user, Lang.FIRST_LOCATION_SET);

            } else if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                user.getCache().set("second-location", event.getClickedBlock().getLocation());
                Lang.sendTo(user, Lang.SECOND_LOCATION_SET);

                event.setCancelled(true);
            }

        }

    }

    /**
     * Listens for the specified event and handles spawn item interaction.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void spawnInteract(PlayerInteractEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(!user.getKit().isPresent() && event.getItem() != null && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()) {
            String name = event.getItem().getItemMeta().getDisplayName();

            if(Chat.color(Config.get(Config.KIT_SELECTOR_NAME)).equals(name)) {
                KitSelector.create(user).open(user);

            } else if(Chat.color(Config.get(Config.KIT_SHOP_NAME)).equals(name)) {
                KitShop.create(user).open(user);

            } else if(Chat.color(Config.get(Config.PREVIOUS_KIT_NAME)).equals(name)) {
                Kit.get(user.getCache().get("previous-kit", "pvp")).get().apply(user);
            }

        }

    }

    /**
     * Listens for the specified event and handles kit interaction.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void kitInteract(PlayerInteractEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(event.getItem() != null && user.getKit().isPresent()) {
            if(event.getItem().getType().equals(Material.MUSHROOM_SOUP)) {
                Player player = user.toPlayer();

                if(player.getHealth() == player.getMaxHealth()) {
                    return;
                }

                double possible = player.getHealth() + Config.getInt(Config.MUSHROOM_HEAL);

                user.toPlayer().setHealth(possible <= player.getMaxHealth() ? possible : player.getMaxHealth());
                event.getItem().setType(Material.BOWL);

            } else {
                user.getKit().get().getCallback().interact(user, event.getItem(), event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK));
            }

        }

    }

}
