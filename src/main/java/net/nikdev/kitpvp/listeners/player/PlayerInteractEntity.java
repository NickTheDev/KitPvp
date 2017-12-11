package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Bukkit;
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
     * Listens for the specified event for handling Donkey Kong's ability.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void donkeyKongInteract(PlayerInteractEntityEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(event.getRightClicked() instanceof Player && user.toPlayer().getPassenger() != event.getRightClicked() && user.getKit().isPresent() && user.getKit().get().getId().equals("donkey-kong")) {
            User victim = User.get(event.getRightClicked().getUniqueId()).get();
            ItemStack item = event.getPlayer().getItemInHand();

            if(item != null && item.getType().equals(Material.INK_SACK)) {
                Lang.sendTo(user, Lang.DONKEY_KONG_PICKUP, Placeholder.of("name", victim.getName()));

                victim.getCache().set("picked-up", true);
                user.toPlayer().setPassenger(victim.toPlayer());

                Bukkit.getScheduler().runTaskLater(KitPvp.get(), () -> {
                    user.getCache().remove("picked-up");
                    user.toPlayer().eject();

                    victim.toPlayer().setVelocity(user.toPlayer().getEyeLocation().getDirection().multiply(1.1));
                    victim.toPlayer().damage(5.5);
                }, 25);

            }

        }

    }

}
