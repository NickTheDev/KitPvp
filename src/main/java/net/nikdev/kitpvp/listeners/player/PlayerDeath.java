package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.Streak;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.packet.Title;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Listener implementation for the {@link PlayerDeathEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class PlayerDeath implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        User victim = User.get(event.getEntity().getUniqueId()).get();
        User killer = event.getEntity().getKiller() != null ? User.get(event.getEntity().getKiller().getUniqueId()).get() : null;

        if(!Lang.get(Lang.PLAYER_DEATH).isEmpty()) {
            event.setDeathMessage(Lang.get(Lang.PLAYER_DEATH, Placeholder.of("name", victim.getName()), Placeholder.of("cause", killer != null ? killer.getName() : "The World")));
        }

        Streak.apply(killer);

        event.setKeepInventory(false);
        event.setDroppedExp(0);
        event.getDrops().clear();
        victim.clean();
        victim.toPlayer().setGameMode(GameMode.SPECTATOR);
        victim.getCache().remove("kit");

        new BukkitRunnable() {

            private int remaining = Config.getInt(Config.RESPAWN_TIME);

            @Override
            public void run() {
                if(remaining == 0) {
                    victim.spawn();

                    cancel();

                } else {
                    Title.builder().title("&e&l" + remaining).fadeIn(1).stay(2).fadeOut(1).build().send(victim);
                    remaining--;
                }

            }

        }.runTaskTimer(KitPvp.get(), 0, 20);

    }

}
