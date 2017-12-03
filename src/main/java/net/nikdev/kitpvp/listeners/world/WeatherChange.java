package net.nikdev.kitpvp.listeners.world;

import net.nikdev.kitpvp.config.ConfigKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static net.nikdev.kitpvp.config.ConfigKeys.ALLOW_RAIN;

/**
 * Listener implementation for the {@link WeatherChangeEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class WeatherChange implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void weatherChange(WeatherChangeEvent event) {
        if(!ConfigKeys.getBool(ALLOW_RAIN)) {
            if(event.toWeatherState()) {
                event.setCancelled(true);
            }

        }

    }

}
