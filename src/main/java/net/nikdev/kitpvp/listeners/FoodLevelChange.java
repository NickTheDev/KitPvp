package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.lang.ConfigKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import static net.nikdev.kitpvp.lang.ConfigKeys.ALLOW_HUNGER;

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
        if(!ConfigKeys.getBool(ALLOW_HUNGER)) {
            event.setCancelled(true);
        }

    }

}
