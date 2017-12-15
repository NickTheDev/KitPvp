package net.nikdev.kitpvp.listeners.player;

import net.nikdev.kitpvp.KitPvp;
import net.nikdev.kitpvp.config.Config;
import net.nikdev.kitpvp.config.lang.Lang;
import net.nikdev.kitpvp.config.lang.Placeholder;
import net.nikdev.kitpvp.user.UserStreak;
import net.nikdev.kitpvp.user.User;
import net.nikdev.kitpvp.util.item.ItemBuilder;
import net.nikdev.kitpvp.util.packet.Title;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

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

        if(event.getEntity().getKiller() != null) {
            User killer = User.get(event.getEntity().getKiller().getUniqueId()).get();

            applyStreaks(killer);
            event.setDeathMessage(Lang.get(Lang.PLAYER_DEATH, Placeholder.of("name", victim.getName()), Placeholder.of("cause", killer.getName())));

        } else {
            event.setDeathMessage(Lang.get(Lang.PLAYER_DEATH, Placeholder.of("name", victim.getName()), Placeholder.of("cause", "The World")));
        }

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

    /**
     * Applies all applicable (lol) streaks to the user according to his current kill streak.
     *
     * @param user User to apply streaks to.
     */
    private void applyStreaks(User user) {
        user.getCache().set("kills", user.getCache().get("kills", 0) + 1);

        List<String> achieved = user.getCache().get("achieved-streaks", new ArrayList<>());
        int kills = user.getCache().get("kills");
        int tokens = Config.getInt(Config.DEFAULT_AWARD);

        for(UserStreak streak : KitPvp.get().getUserManager().getStreaks()) {
            if(!achieved.contains(streak.getName()) && kills >= streak.getKillsNeeded()) {
                tokens += streak.getAward();
                achieved.add(streak.getName());

                Lang.sendTo(user, Lang.PLAYER_STREAK, Placeholder.of("name", streak.getName()), Placeholder.of("amount", streak.getAward()));
            }

        }

        user.getCache().set("achieved-streaks", achieved);
        user.getStats().addTokens(tokens);
        Title.builder().title(Lang.get(Lang.DEATH_TOKEN_TITLE, Placeholder.of("amount", tokens))).fadeIn(1).fadeOut(1).stay(2).build().send(user);

        // Putting this code here because we know there is a killer.
        if(user.getKit().get().getId().equals("warper")) {
            user.give(ItemBuilder.builder(Material.ENDER_PEARL));
        }

    }

}
