package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.kit.Cooldowns;
import net.nikdev.kitpvp.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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

    /**
     * Listens for the specified event for handling Dragon Born's ability.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void dragonBornInteract(PlayerInteractEntityEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(user.getKit().isPresent() && user.getKit().get().getId().equals("dragon-born")) {
            ItemStack item = event.getPlayer().getItemInHand();

            if(item != null && item.getType().equals(Material.SKULL_ITEM)) {
                if(Cooldowns.check(user, "dragon-push")) {
                    return;
                }

                event.getRightClicked().setVelocity(event.getRightClicked().getLocation().toVector().subtract(user.toPlayer().getLocation().toVector()).normalize().multiply(2.6));

                Cooldowns.start(user, "dragon-push", 200);
            }

        }

    }

    /**
     * Listens for the specified event for handling Dragon Born's ability.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void vampireInteract(PlayerInteractEntityEvent event) {
        User user = User.get(event.getPlayer().getUniqueId()).get();

        if(event.getRightClicked() instanceof Player && user.getKit().isPresent() && user.getKit().get().getId().equals("vampire")) {
            ItemStack item = event.getPlayer().getItemInHand();
            User victim = User.get(event.getRightClicked().getUniqueId()).get();

            if(item != null && item.getType().equals(Material.SPIDER_EYE) && !user.getCache().contains("vampire-has-victim") && !victim.getCache().contains("vampire-victim")) {
                user.getCache().set("vampire-has-victim", true);
                victim.getCache().set("vampire-victim", true);

                new BukkitRunnable() {

                    @Override
                    public void run() {
                        if(!victim.getCache().contains("vampire-victim")) {
                            user.getCache().remove("vampire-has-victim");
                            cancel();

                            return;
                        }

                        victim.toPlayer().damage(6);
                    }

                }.runTaskTimer(KitPvp.get(), 0, 20);

            }

        }

    }

}
