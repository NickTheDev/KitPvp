package net.nikdev.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Listener implementation for the {@link FoodLevelChangeEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class FoodLevelChange implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void foodLevelChanges(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

}
